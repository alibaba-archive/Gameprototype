using UnityEngine;
using System.Collections;
using System;
using System.Net;
using System.Net.Sockets;
using System.Collections.Generic;
using System.IO;
using Google.ProtocolBuffers;

public class NetWorkScript {

    /// <summary>
    /// 全局唯一的连接对象实例
    /// </summary>
    private static NetWorkScript instance;

    private byte[] readBuff = new byte[1024]; // socket 缓冲区
    private bool isRead = false;
    private List<byte> ioBuff = new List<byte>(); // 待解析的消息存储数组
    public List<SocketModelPB> messageList = new List<SocketModelPB>();
    private Socket socket;

    public static NetWorkScript Instance {
        get {
            if (instance == null) {
                instance = new NetWorkScript();
                instance.init();
            }
            return instance;
        }
    }

    private void init() {
        try
        {
            socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            socket.Connect("127.0.0.1", 9090);
            socket.BeginReceive(readBuff, 0, 1024, SocketFlags.None, ReceiveCallBack, readBuff);
            Debug.Log("连接服务器成功");
        }
        catch (Exception e) {
            Debug.Log("连接服务器失败");
        }
    }

    public void write(int type, int area, int command,  ByteString contentMsg)
    {
        SocketModelPB.Builder builder = SocketModelPB.CreateBuilder();
        builder.SetArea(area);
        builder.SetType(type);
        builder.SetCommand(command);
        builder.SetContent(contentMsg);
        SocketModelPB msg  = builder.Build();
        MemoryStream stream = new MemoryStream();
        CodedOutputStream outStream = CodedOutputStream.CreateInstance(stream);
        outStream.WriteMessageNoTag(msg);
        outStream.Flush();
        try
        {
            socket.Send(stream.ToArray());
        }
        catch
        {
            Debug.LogError("网络错误，请重新登录");
        }
    }


    private void ReceiveCallBack(IAsyncResult ar) {
        try
        {
            //结束异步消息读取 并获取消息长度
            int readCount = socket.EndReceive(ar);
            byte[] bytes = new byte[readCount];
            //将接收缓冲池的内容复制到临时消息存储数组
            Buffer.BlockCopy(readBuff, 0, bytes, 0, readCount);
            ioBuff.AddRange(bytes);
            if (!isRead)
            {
                //如果当前不在解析消息，开始解析处理
                isRead = true;
                onData();
            }
        }
        catch (Exception e) {
            Debug.LogError(e.Message);
            Debug.LogError("Receive data 异常");
            socket.Close();
            return;
        }

        socket.BeginReceive(readBuff, 0, 1024, SocketFlags.None, ReceiveCallBack, readBuff);
    }

    private void onData() {
        CodedInputStream stream = CodedInputStream.CreateInstance(ioBuff.ToArray());
        if (stream.IsAtEnd)
        {
            isRead = false;
            return;
        }
        // 读取头信息转为int，为body的长度
        int dataSize;
        try
        {
            dataSize = (int)stream.ReadRawVarint32();
        }
        catch (Exception e)
        {
            // 读header失败，有可能是消息不完整，需要等待下一个消息。
            isRead = false;
            return;
        }
        int headerSize = (int)stream.Position;
        // 判断是否已经读到一次完整的信息
        if (dataSize > ioBuff.Count - headerSize)
         {
              //body长度不够 等待下个消息的到来
             isRead = false;
            return;
        }

        // 解析消息体
        byte[] msg = stream.ReadRawBytes(dataSize);
        SocketModelPB socketMsg = SocketModelPB.ParseFrom(msg);
        messageList.Add(socketMsg);

        //从消息数组中去掉已经解析完成的数据，保留缓存区的剩余数据
         ioBuff.RemoveRange(0, headerSize + dataSize);
        onData();           
    }
}
