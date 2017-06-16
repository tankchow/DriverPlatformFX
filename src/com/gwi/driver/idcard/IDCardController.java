package com.gwi.driver.idcard;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.gwi.driver.BaseController;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IDCardController extends BaseController implements Initializable {

	private GWI_IDCard_Driver dll;

	@FXML
	private TextArea MsgText;// 记录日志信息

	@FXML
	private TextField txName;// 姓名

	@FXML
	private TextField txAdress;// 地址

	@FXML
	private TextField txSex;// 性别

	@FXML
	private TextField txMz;// 民族

	@FXML
	private TextField txBirthday;// 出生年月

	@FXML
	private TextField txNumber;// 身份证号码

	@FXML
	private TextField txJG;// 机构

	@FXML
	private TextField txDate;// 有效期

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public IDCardController() {

		dll = (GWI_IDCard_Driver) Native.loadLibrary("IDCard/GWI_IDCard_Driver", GWI_IDCard_Driver.class);
	}

	private Pointer pszRcCodePointer = new Memory(260L);

	DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@FXML
	public void Return_Click() {
		super.close();
		//mainStage.show();
	}

	@FXML
	public void Open_Click() {
		try {
			int setDeviceParamResult = dll.IDCard_OpenDevice(pszRcCodePointer);
			String msg = df.format(new Date()) + "-->" + "打开设备结果：" + (setDeviceParamResult == 0 ? "成功" : "失败");
			System.out.println(msg);
			this.MsgText.appendText(msg + "\n");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	@FXML
	public void Close_Click() {
		try {
			int setDeviceParamResult = dll.IDCard_CloseDevice(pszRcCodePointer);
			String msg = df.format(new Date()) + "-->" + "关闭设备结果返回：" + (setDeviceParamResult == 0 ? "成功" : "失败");
			System.out.println(msg);
			this.MsgText.appendText(msg + "\n");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	@FXML
	public void ReadDivce_Click() {
		try {
			Pointer deviceID = new Memory(260L);
			deviceID.setString(0, "");
			dll.IDCard_GetDevid(5000, deviceID, pszRcCodePointer);
			String msg = df.format(new Date()) + "-->" + "读取设备信息返回：" + deviceID.getString(0, "UTF-8");
			System.out.println(msg);
			this.MsgText.appendText(msg + "\n");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@FXML
	public void ReadNum_Click() {
		try {
			Pointer cardID = new Memory(260L);
			cardID.setString(0, "");
			dll.IDCard_GetCardid(5000, cardID, pszRcCodePointer);
			String card = cardID.getString(0, "UTF-8");
			String msg = df.format(new Date()) + "-->" + "读取身份证ID返回：" + (card.isEmpty() ? "失败" : card);
			System.out.println(msg);
			this.MsgText.appendText(msg + "\n");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@FXML
	public void Read_Click() {
		try {
			Pointer cardMsg = new Memory(260L);
			dll.IDCard_ReadIDCardMsg(5000, cardMsg, pszRcCodePointer);

			String sResult = cardMsg.getString(0, "GBK");
			if (!sResult.isEmpty()) {
				String[] sFields = sResult.split("\\|");
				this.txName.setText(sFields[0].trim());
				this.txSex.setText(sFields[1].trim());
				this.txMz.setText(sFields[2].trim());
				this.txBirthday.setText(sFields[3].trim());
				this.txNumber.setText(sFields[4].trim());
				this.txAdress.setText(sFields[5].trim());
				this.txJG.setText(sFields[6].trim());
				this.txDate.setText(sFields[7].trim() + "~" + sFields[8].trim());
			}

			String msg = df.format(new Date()) + "-->" + "读取身份证信息返回：" + (sResult.isEmpty() ? "失败" : "成功");
			System.out.println(msg);
			this.MsgText.appendText(msg + "\n");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@FXML
	public void SetPram_Click() {
		try {
			pszRcCodePointer.setString(0, "");
			Pointer devType = new Memory(260L);
			devType.setString(0, "GWI");
			Pointer devPort = new Memory(260L);
			devPort.setString(0, "USB");
			Pointer devPortParam = new Memory(260L);
			devPortParam.setString(0, "9600,n,8,1");
			int setDeviceParamResult = dll.IDCard_SetDeviceParam(devType, devPort, devPortParam, pszRcCodePointer);
			String msg = df.format(new Date()) + "-->" + "设置参数结果：" + (setDeviceParamResult == 0 ? "成功" : "失败");
			System.out.println(msg);
			this.MsgText.appendText(msg + "\n");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@FXML
	public void Clear_Click() {
		this.txName.setText("");
		this.txSex.setText("");
		this.txMz.setText("");
		this.txBirthday.setText("");
		this.txNumber.setText("");
		this.txAdress.setText("");
		this.txJG.setText("");
		this.txDate.setText("");
		this.MsgText.setText("");
	}

}
