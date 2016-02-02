// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CodeUtil.java

package util;

import codeGenerate.def.FtlDef;
import codeGenerate.factory.CodeGenerateFactory;

public class CodeUtil
{

	public CodeUtil()
	{
	}

	public static void main(String args[])
	{
		String tableName = "CH_TRAN_CONFIGURE";
		String codeName = "支付交易配置管理";
		CodeGenerateFactory.codeGenerateByFTL(tableName, codeName, FtlDef.KEY_TYPE_02);
	}
}
