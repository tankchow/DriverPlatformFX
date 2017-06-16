package com.gwi.driver.idcard;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface GWI_IDCard_Driver extends Library {

	int IDCard_SetDeviceParam(Pointer devType, Pointer devPort, Pointer devPortParam, Pointer pszRcCode);

	int IDCard_OpenDevice(Pointer pszRcCode);

	int IDCard_CloseDevice(Pointer pszRcCode);

	void IDCard_SetDeviceTraceLevel(int level);

	int IDCard_GetDevid(int dwTimeOut, Pointer devid, Pointer pszRcCode);

	int IDCard_ReadIDCardMsg(int dwTimeOut, Pointer msg, Pointer pszRcCode);

	int IDCard_GetCardid(int dwTimeOut, Pointer cardid, Pointer pszRcCode);

	int IDCard_ReadIDCardMsgNum(int dwTimeOut, Pointer msg, Pointer pszRcCode);

	int IDCard_ReadIDCardMsgExt(Pointer photoName, int dwTimeOut, Pointer msg, Pointer pszRcCode);

	int IDCard_ReadIDCardMsgNumExt(Pointer photoName, int dwTimeOut, Pointer msg, Pointer pszRcCode);

}
