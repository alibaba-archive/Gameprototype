using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


  public  class SocketModel
    {
      public int type { get; set; }
      public int area { get; set; }
      public int command { get; set; }
      public object message {get;set;}

      public SocketModel() { }

      public SocketModel(int type,int area,int command,object message) {
          this.type = type;
          this.area = area;
          this.command = command;
          this.message = message;
      }

      public T getMessage<T>() {
          return (T)message;
      }
    }

