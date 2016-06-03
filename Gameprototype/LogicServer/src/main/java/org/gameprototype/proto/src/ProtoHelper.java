package org.gameprototype.proto.src;

/**
 * Created by haihong.xiahh on 2015/11/3.
 */
public class ProtoHelper {
    public static CommonRespDef.CommonResp genCommonResp(CommonRespDef.CommonResp.RespCode respCode) {
        return  CommonRespDef.CommonResp.newBuilder().setCode(respCode).build();
    }

    public static CommonRespDef.CommonResp genCommonResp(int respCode) {
        return  CommonRespDef.CommonResp.newBuilder().setCode(CommonRespDef.CommonResp.RespCode.valueOf(respCode)).build();
    }

}
