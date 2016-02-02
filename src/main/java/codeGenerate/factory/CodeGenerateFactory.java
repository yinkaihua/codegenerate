package codeGenerate.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.StringUtils;
import codeGenerate.ColumnData;
import codeGenerate.CreateBean;
import codeGenerate.def.CodeResourceUtil;
import codeGenerate.def.FreemarkerEngine;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class CodeGenerateFactory{

	private static String url;
	private static String username;
	private static String passWord;
	private static String databaseName;

	public CodeGenerateFactory() {
	}

	public static void codeGenerateByFTL(String tableName, String codeName, String keyType) {
		try {
			tableName = tableName==null?"":tableName.toUpperCase();
			System.out.println("----------------------------"+tableName+"持久化模板代码生成开始---------------------------");
			CreateBean createBean = new CreateBean();
			createBean.setMysqlInfo(url, username, passWord,databaseName);
			String className = createBean.getTablesNameToClassName(tableName);
			String commonName = className.substring(1).toLowerCase();
			String lowerName = (new StringBuilder(String.valueOf(className.substring(0, 1).toLowerCase()))).append(
					className.substring(1, className.length())).toString();
			String tableNameUpper = tableName.toUpperCase();
			String tablesAsName = createBean.getTablesASName(tableName);
			if (StringUtils.isEmpty(codeName)) {
				Map<String, String> tableCommentMap = createBean.getTableCommentMap();
				codeName = (String) tableCommentMap.get(tableNameUpper);
				codeName = codeName== null ? "": codeName.trim();
			}
			String getBestMatchedTables = CodeResourceUtil.getConfigInfo("getBestMatched_tables");
			getBestMatchedTables = getBestMatchedTables== null ? "": getBestMatchedTables.trim().toUpperCase();
			String getBestMatchedFlag = "N";
			if(getBestMatchedTables!= null){
				getBestMatchedFlag = getBestMatchedTables.contains(tableNameUpper)?"Y":"N";
			}
			String resourcePathSrc = CodeResourceUtil.getConfigInfo("resource_path_src");
			String classPathSrc = CodeResourceUtil.getConfigInfo("class_path_src");

			String sqlMapperPackage = CodeResourceUtil.getConfigInfo("sqlMapper_path");
			String entityPackage = CodeResourceUtil.getConfigInfo("entity_path")+"."+commonName;
			String servicePackage = CodeResourceUtil.getConfigInfo("service_path")+"."+commonName;
			String serviceImpPackage = servicePackage+".impl.";
			String daoPackage = CodeResourceUtil.getConfigInfo("dao_path")+"."+commonName;
			String daoImpPackage = daoPackage+".impl.";
			
			String sqlMapperPath = (new StringBuilder(String.valueOf(sqlMapperPackage.replace(".", "\\")))).append("\\")
					.append(className).append("Mapper.xml").toString();
			String entityPath = (new StringBuilder(String.valueOf(entityPackage.replace(".", "\\")))).append("\\")
					.append(className).append(".java").toString();
			String servicePath = (new StringBuilder(String.valueOf(servicePackage.replace(".", "\\")))).append("\\")
					.append(className).append("Service.java").toString();
			String serviceImpPath = (new StringBuilder(String.valueOf(serviceImpPackage.replace(".", "\\")))).append("\\")
					.append(className).append("ServiceImpl.java").toString();
			String daoPath = (new StringBuilder(String.valueOf(daoPackage.replace(".", "\\")))).append("\\")
					.append(className).append("DAO.java").toString();
			String daoImplPath = (new StringBuilder(String.valueOf(daoImpPackage.replace(".", "\\")))).append("\\")
					.append(className).append("DAOImpl.java").toString();
			String sqlMapperFlag = CodeResourceUtil.getConfigInfo("sqlMapper_flag");
			String domainFlag = CodeResourceUtil.getConfigInfo("entity_flag");
			String serviceFlag = CodeResourceUtil.getConfigInfo("service_flag");
			String serviceImplFlag = CodeResourceUtil.getConfigInfo("service_imp_flag");
			String daoFlag = CodeResourceUtil.getConfigInfo("dao_flag");
			String daoImplFlag = CodeResourceUtil.getConfigInfo("dao_impl_flag");
			Map<String, Object> sqlMap = createBean.getAutoCreateSql(tableName);
			List<ColumnData> columnDatas = createBean.getColumnDatas(tableName);
			List<ColumnData> columnKeyDatas = createBean.getColumnKeyDatas(columnDatas);
			String columnKeyParam = createBean.getColumnKeyParam(columnKeyDatas);
			String columnKeyUseParam = createBean.getColumnKeyUseParam(columnKeyDatas);
			String columnKeySort = createBean.getColumnKeySort(columnKeyDatas);
			String ignoreGenerateColumns = CodeResourceUtil.getConfigInfo("ignore_generate_columns");
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
			String nowDate = dateformat.format(new Date());
			System.out.println((new StringBuilder("开始生成时间:")).append(nowDate).toString());
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("className", className);
			root.put("lowerName", lowerName);
			root.put("codeName", codeName);
			root.put("tableName", tableName);
			root.put("tableNameUpper", tableNameUpper);
			root.put("tablesAsName", tablesAsName);
			root.put("entityPackage", entityPackage);
			root.put("servicePackage", servicePackage);
			root.put("serviceImpPackage", serviceImpPackage);
			root.put("daoPackage", daoPackage);
			root.put("daoImpPackage", daoImpPackage);
			root.put("keyType", keyType);
			root.put("nowDate", nowDate);
			root.put("feilds", createBean.getBeanFeilds(tableName, className));
			//root.put("queryfeilds", createBean.getQueryBeanFeilds(tableName, className));
			root.put("columnDatas", columnDatas);
			root.put("columnKeyDatas", columnKeyDatas);
			root.put("columnKeyParam", columnKeyParam);
			root.put("columnKeyUseParam", columnKeyUseParam);
			root.put("columnKeySort", columnKeySort);
			root.put("beanFieldDataTypes", createBean.getBeanFieldDataTypes());
			root.put("SQL", sqlMap);
			root.put("getBestMatchedFlag", getBestMatchedFlag);
			root.put("columnKeyString", columnKeySort.replace(",", ":"));
			root.put("ignoreGenerateColumns", ignoreGenerateColumns);
			Configuration cfg = new Configuration();
			String templateBasePath = (new StringBuilder(String.valueOf(getProjectPath()))).append(
					CodeResourceUtil.getTEMPLATEPATH()).toString();
			cfg.setDirectoryForTemplateLoading(new File(templateBasePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			if ("Y".equals(sqlMapperFlag)){
				FreemarkerEngine.createFileByFTL(cfg, root, "sqlMapper.ftl", resourcePathSrc, sqlMapperPath);
			}
			if ("Y".equals(domainFlag)){
				FreemarkerEngine.createFileByFTL(cfg, root, "entityClass.ftl", classPathSrc, entityPath);
			}
			if ("Y".equals(serviceFlag)){
				FreemarkerEngine.createFileByFTL(cfg, root, "serviceClass.ftl", classPathSrc, servicePath);
			}
			if ("Y".equals(serviceImplFlag)){
				FreemarkerEngine.createFileByFTL(cfg, root, "serviceImplClass.ftl", classPathSrc, serviceImpPath);
			}
			if ("Y".equals(daoFlag)){
				FreemarkerEngine.createFileByFTL(cfg, root, "daoClass.ftl", classPathSrc, daoPath);
			}
			if ("Y".equals(daoImplFlag)){
				FreemarkerEngine.createFileByFTL(cfg, root, "daoImplClass.ftl", classPathSrc, daoImplPath);
			}
			System.out.println("----------------------------"+tableNameUpper+"持久化模板代码生成完毕---------------------------");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static String getProjectPath() {
		String path = (new StringBuilder(String.valueOf(System.getProperty("user.dir").replace("\\", "/"))))
				.append("/").toString();
		path += "target/";
		System.out.println("模板路径："+path);
		return path;
	}

	static {
		url = CodeResourceUtil.URL;
		username = CodeResourceUtil.USERNAME;
		passWord = CodeResourceUtil.PASSWORD;
		databaseName = CodeResourceUtil.DATABASE_NAME;
	}
}
