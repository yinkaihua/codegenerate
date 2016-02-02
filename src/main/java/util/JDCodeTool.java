package util;

import codeGenerate.def.CodeResourceUtil;
import codeGenerate.def.FtlDef;
import codeGenerate.factory.CodeGenerateFactory;

public class JDCodeTool{

	public static void main(String args[]) {
		String codeCgTables = CodeResourceUtil.getConfigInfo("code_cg_tables");
		if (StringUtils.isEmpty(codeCgTables)) {
			if (CodeResourceUtil.getConfigInfo("genTablesAllIfUnDefindTables").toUpperCase().equals("Y")) {
				codeCgTables = QueryTable.getAllTables();
				if (StringUtils.isEmpty(codeCgTables)) {
					return;
				}
			} else {
				return;
			}
		}
		String tables[] = codeCgTables.split(",");
		for (int i = 0; i < tables.length; i++) {
			CodeGenerateFactory.codeGenerateByFTL(tables[i], "", FtlDef.KEY_TYPE_02);
		}
	}
}
