package com.mogujie.tt.packet.base;

import com.mogujie.tt.config.ProtocolConstant;
import com.mogujie.tt.config.SysConstant;
import com.mogujie.tt.log.Logger;

/**
 * TCP协议的头文件
 * 
 * @author dolphinWang
 * @time 2014/04/30
 */
public class Header {

	private Logger logger = Logger.getLogger(Header.class);

	private int length; // 数据包长度，包括包头


	private int serviceId; // SID

	private int commandId; // CID
	private int version; // 版本号

	private int reserved; // 保留，可用于如序列号等

	public Header() {
		length = 0;
		version = 0;
		serviceId = 0;
		commandId = 0;
		reserved = 0;
	}

	/**
	 * 头文件的压包函数
	 * 
	 * @return 数据包
	 */
	public DataBuffer encode() {
		DataBuffer db = new DataBuffer(SysConstant.PROTOCOL_HEADER_LENGTH);
		db.writeInt(length);
		db.writeChar((char) serviceId);
		db.writeChar((char) commandId);
		db.writeChar((char)version);
		logger.d("packet#header encode -> length:%d,  seviceId:%d, commandId:%d, version:%d, reserved:%d", length, serviceId, commandId, version, reserved);
		db.writeChar((char)reserved);
		return db;
	}
	
	/**
	 * 头文件的解包函数
	 * 
	 * @param buffer
	 */
	public void decode(DataBuffer buffer) {
		if (null == buffer)
			return;
		try {
			length = buffer.readInt();
			serviceId = buffer.readChar();
			commandId = buffer.readChar();
			version = buffer.readChar();
			reserved = buffer.readChar();

			logger.d(
					"decode header, length:%d, version:%d, serviceId:%d, commandId:%d, reserved:%d",
					length, version, serviceId, commandId,
					reserved);

		} catch (Exception e) {
			logger.e(e.getMessage());
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String
				.format("decode header, length:%d, version:%d,  serviceId:%d, commandId:%d, reserved:%d",
						length, version,  serviceId, commandId, 
						reserved);

	}

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandID) {
		this.commandId = commandID;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceID) {
		this.serviceId = serviceID;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
}
