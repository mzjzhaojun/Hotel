package com.yichuang.kyjd.commnd.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.yichuang.kyjd.commnd.base.GetSqlCon;

public class Flex {

	private static Flex flex;

	private Flex() {
		init();
	}

	public static Flex getInstance() {
		if (flex == null) {
			flex = new Flex();
		}
		return flex;
	}

	// 数据库名称
	public static String dbName = "";
	// 项目文件地址
	public static String filePath = "";
	// 项目基本包
	public static String basePage = "";
	// 项目封装包
	public static String commndPage = "";

	public static String osName = "";

	public static String classesByPage = "";

	public static String projectName = "";
	//
	public static File file;

	public static GetSqlCon sqlcon = GetSqlCon.getInstance();

	private static String FILE_PATH_NAME = "jdbc.properties";

	private void init() {
		try {
			InputStream in = Flex.class.getClassLoader().getResourceAsStream(
					FILE_PATH_NAME);
			Properties props = new Properties();

			props.load(in);
			in.close();
			dbName = props.getProperty("Zj.dbName");
			filePath = props.getProperty("Zj.filePath");
			basePage = props.getProperty("Zj.basePage");
			commndPage = props.getProperty("Zj.commndPage");
			osName = props.getProperty("Zj.osName");
			classesByPage = props.getProperty("Zj.classesByPage");
			projectName = props.getProperty("Zj.projectName");
			createFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * 
	 */
	public static void createFile() {
		file = new File(filePath + "controller");
		file.mkdirs();
		file = new File(filePath + "entity");
		file.mkdirs();
		file = new File(filePath + "mapper");
		file.mkdirs();
	}

	/**
	 * 
	 * 
	 * @param table
	 */
	public static void createNewFile(String table) {
		file = new File(filePath + "dao/" + table.toLowerCase() + "/impl");
		file.mkdirs();
		file = new File(filePath + "service/" + table.toLowerCase() + "/impl");
		file.mkdirs();
		file = new File(filePath + "controller/" + table.toLowerCase());
		file.mkdirs();
		file = new File(filePath + "entity/" + table.toLowerCase());
		file.mkdirs();
		file = new File(filePath + "mapper/" + table.toLowerCase());
		file.mkdirs();
	}

	/**
	 * 
	 * 
	 * @param table
	 * @return
	 */
	public static File getFileDao(String table) throws Exception {
		file = new File(filePath + "dao/" + table.toLowerCase() + "/I" + table
				+ "Dao.java");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		return file;
	}

	/**
	 * 
	 * 
	 * @param table
	 * @return
	 */
	public static File getFileDaoImpl(String table) throws Exception {
		file = new File(filePath + "dao/" + table.toLowerCase() + "/impl/"
				+ table + "DaoImpl.java");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		return file;
	}

	/**
	 * 
	 * 
	 * @param table
	 * @return
	 */
	public static File getService(String table) throws Exception {
		file = new File(filePath + "service/" + table.toLowerCase() + "/I"
				+ table + "Service.java");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		return file;
	}

	/**
	 * 
	 * 
	 * @param table
	 * @return
	 */
	public static File getServiceImpl(String table) throws Exception {
		file = new File(filePath + "service/" + table.toLowerCase() + "/impl/"
				+ table + "ServiceImpl.java");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		return file;
	}

	/**
	 * 
	 * @return
	 */
	public static File getController(String table) throws Exception {
		file = new File(filePath + "controller/" + table.toLowerCase() + "/"
				+ table + "Controller.java");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		return file;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static File getEntity(String table) throws Exception {
		file = new File(filePath + "entity/" + table.toLowerCase() + "/"
				+ table + ".java");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		return file;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static File getMapper(String table) throws Exception {
		file = new File(filePath + "mapper/" + table.toLowerCase() + "/"
				+ table + "Mapper.xml");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		return file;
	}

	public static void main(String[] args) {
		insertData(500000);
		System.out.println("complate");
	}

	public static void insertData(int size) {
		try {
			Connection conn = sqlcon.getCon();

			conn.setAutoCommit(false);

			// insert into user(name, password) value (?, ?)
			// insert into scott(Name, age,sex) value (?, ?,?)
			// insert into tiger(Name, age,sex) value (?, ?,?)
			// 构造预处理statement
			PreparedStatement pst = conn
					.prepareStatement("insert into data(Name, NameCode,Password,roleid,status) value (?,?,?,?,?)");
			for (int i = 1; i <= size; i++) {
				pst.setString(1, "我是中文姓名有三十二个字符我是中文姓名有三十二个字符" + i);
				pst.setString(2, "我是姓名有三十二个字符我是中文姓名有三十二个字符CODECODECODECODECODE"
						+ i);
				pst.setString(3,
						"我是姓名有三十二个字符我是中文姓名有三十二个字符PasswordPasswordPassword" + i);
				pst.setInt(4, 5);
				pst.setInt(5, 16);
				pst.addBatch();
				// 每1000次提交一次
				if (i % 10000 == 0) {// 可以设置不同的大小；如50，100，500，1000等等
					pst.executeBatch();
					conn.commit();
					pst.clearBatch();
					System.out.println(i + "");
				}
			}
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Object> getTableMySql() {
		List<Object> list = new ArrayList<Object>();
		try {
			Connection conn = sqlcon.getCon();
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = null;
			String table_name = "TABLE_NAME";
			if (sqlcon.getType().equals("sqlServer")) {
				table_name = "Name";
				rs = stmt.executeQuery("SELECT " + table_name
						+ " FROM SysObjects Where XType='U'");
			} else {
				table_name = "TABLE_NAME";
				rs = stmt
						.executeQuery("SELECT "
								+ table_name
								+ " FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"
								+ dbName + "'");
			}
			while (rs.next()) {
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("name", rs.getString(table_name));
				list.add(hm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void printFileMySql(String tableNames) {
		// 读取数据1
		try {
			Connection conn = sqlcon.getCon();
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = null;
			tableNames = tableNames.substring(0, tableNames.length() - 1);
			String[] tss = tableNames.split(",");
			if (tss == null || filePath == null || filePath == "")
				return;
			List<String[]> db2java = new ArrayList<String[]>();
			db2java.add(new String[] { "bit", "Boolean" });
			db2java.add(new String[] { "varchar", "String" });
			db2java.add(new String[] { "numeric", "Double" });
			db2java.add(new String[] { "double", "Double" });
			db2java.add(new String[] { "int", "Integer" });
			// db2java.add(new String[] { "datetime", "java.util.Date" });
			// db2java.add(new String[] { "date", "java.util.Date" });
			db2java.add(new String[] { "datetime", "String" });
			db2java.add(new String[] { "date", "String" });
			db2java.add(new String[] { "tinyint", "Integer" });
			db2java.add(new String[] { "smallint", "Integer" });
			db2java.add(new String[] { "bigint", "Integer" });
			db2java.add(new String[] { "char", "String" });
			db2java.add(new String[] { "float", "float" });
			for (int ti = 0; ti < tss.length; ti++) {
				// 表名
				String tb = tss[ti];
				String tn = tb.substring(tb.indexOf("_") + 1).toLowerCase();
				createNewFile(tn);
				HashMap[] r = (HashMap[]) null;
				// 读取数据2
				int iRowNum;
				r = (HashMap[]) null;
				iRowNum = 0;
				int iColCnt = 0;
				if (sqlcon.getType().equals("sqlServer")) {
					rs = stmt
							.executeQuery("select column_name,data_type from information_schema.columns where table_name = '"
									+ tb + "'");
				} else {
					rs = stmt
							.executeQuery("select column_name,data_type from information_schema.columns where table_schema = '"
									+ dbName + "' and table_name='" + tb + "'");
				}
				ResultSetMetaData MetaData = rs.getMetaData();
				iColCnt = MetaData.getColumnCount();
				if (rs.next()) {
					rs.last();
					r = new HashMap[rs.getRow()];
					rs.beforeFirst();
				}
				while (rs.next()) {
					r[iRowNum] = new HashMap();
					for (int j = 0; j < iColCnt; j++) {
						String szColName = MetaData.getColumnName(j + 1);
						String szColValue = rs.getString(szColName);
						if (szColValue == null)
							szColValue = "";
						r[iRowNum].put(szColName, szColValue);
					}
					iRowNum++;
				}

				// 表名
				String bt = tn.substring(0, 1).toUpperCase() + tn.substring(1);
				String bm = tn.toLowerCase() + "." + bt;
				// ============
				// 实体类文件=========================================
				file = getEntity(bt);
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("package " + basePage + ".entity." + tn.toLowerCase()
						+ ";\r\n\n");
				bw.write("/**\r\n");
				bw.write("* @author zj    default  \r\n");
				bw.write("* \r\n");
				bw.write("* @version 1.1\r\n");
				bw.write("*/\r\n");
				bw.write("public class " + bt
						+ " implements java.io.Serializable{\r\n");
				bw.write("\r\n");
				bw.write("  private static final long serialVersionUID=1L;\r\n");
				bw.write("\r\n");
				String[][] ts = new String[r.length][3];
				String data_type = "";
				String column_name = "";
				if (sqlcon.getType().equals("sqlServer")) {
					data_type = "data_type";
					column_name = "column_name";
				} else {
					data_type = "DATA_TYPE";
					column_name = "COLUMN_NAME";
				}
				for (int i = 0; i < r.length; i++) {
					ts[i][0] = "Object";
					for (String[] temp : db2java)
						if (r[i].get(data_type).equals(temp[0]))
							ts[i][0] = temp[1];
					ts[i][1] = r[i].get(column_name).toString().toLowerCase();
					ts[i][2] = ts[i][1].substring(0, 1).toUpperCase()
							+ ts[i][1].substring(1);
					bw.write("  " + ts[i][0] + " " + ts[i][1] + ";\r\n");
				}
				for (int i = 0; i < r.length; i++) {
					bw.write("\r\n");
					bw.write("  public " + ts[i][0] + " get" + ts[i][2]
							+ "(){\r\n");
					bw.write("      return " + ts[i][1] + ";\r\n");
					bw.write("  }\r\n");
					bw.write("\r\n");
					bw.write("  public void set" + ts[i][2] + "(" + ts[i][0]
							+ " " + ts[i][1] + ") {\r\n");
					bw.write("      this." + ts[i][1] + " =" + ts[i][1]
							+ ";\r\n");
					bw.write("  }\r\n");
				}
				bw.write("  public " + bt + "(){\r\n");
				bw.write("  }\r\n");
				String gz = "";
				for (int i = 0; i < r.length; i++)
					gz += "," + ts[i][0] + " " + ts[i][1];
				bw.write("  public " + bt + "(" + gz.substring(1) + "){\r\n");
				for (int i = 0; i < r.length; i++)
					bw.write("      this." + ts[i][1] + "=" + ts[i][1]
							+ ";\r\n");
				bw.write("  }\r\n");
				bw.write("}");
				bw.flush();

				// ========mapper文件============================================
				String column = "", value = "", update = "", insertColumn = "", insertValue = "";
				for (int i = 0; i < r.length; i++) {
					column += "" + ts[i][1] + ",";
					value += "#{" + ts[i][1] + "},";
					update += "" + ts[i][1] + "" + "=#{" + ts[i][1] + "},";
					if (i >= 1) {
						insertColumn += "" + ts[i][1] + ",";
						insertValue += "#{" + ts[i][1] + "},";
					}
				}
				column = (column + ",").replace(",,", "");
				value = (value + ",").replace(",,", "");
				update = (update + ",").replace(",,", "");

				insertColumn = (insertColumn + ",").replace(",,", "");
				insertValue = (insertValue + ",").replace(",,", "");

				file = getMapper(bt);
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
				bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
				bw.write("<mapper namespace=\"" + bt + "\">\r\n");

				// Base_Column_List
				bw.write("  <sql id=\"Base_Column_List\">\r\n");
				bw.write("     " + column + "\r\n");
				bw.write("  </sql>\r\n");

				// ResultMap
				bw.write("  <resultMap id=\"ResultMap\" type=\"" + basePage
						+ ".entity." + bm + "\">\r\n");
				for (int i = 0; i < r.length; i++) {
					bw.write("         <id property=\"" + ts[i][1]
							+ "\" column=\"" + ts[i][1] + "\" />\r\n");
				}
				bw.write("  </resultMap>\r\n");

				// sava
				bw.write("  <insert id=\"save\" parameterType=\"" + basePage
						+ ".entity." + bm + "\">\r\n");
				bw.write("  <selectKey resultType=\"java.lang.Integer\" order=\"AFTER\" keyProperty=\"id\">\r\n");
				bw.write("  	SELECT LAST_INSERT_ID() AS id \r\n");
				bw.write("  </selectKey>\r\n");
				bw.write("  insert into " + tb + " (" + insertColumn + ")\r\n");
				bw.write("         values (" + insertValue + ")\r\n");
				bw.write("  </insert>\r\n");

				// deleteByIds
				bw.write(" <delete id=\"deleteByIds\" parameterType=\"java.util.List\">\r\n");
				bw.write("     delete from " + tb + " where id in \r\n");
				bw.write("        <foreach collection=\"array\" index=\"index\" item=\"item\" open=\"(\"\r\n");
				bw.write("          separator=\",\" close=\")\">\r\n");
				bw.write("           #{item}\r\n");
				bw.write("        </foreach>\r\n");
				bw.write(" </delete>\r\n");

				// update
				bw.write(" <update id=\"update\" parameterType=\"" + basePage
						+ ".entity." + bm + "\">\r\n");
				bw.write("    update " + tb + "\r\n");
				bw.write("         <set>\r\n");
				for (int i = 0; i < r.length; i++) {
					bw.write("             <if test=\"" + ts[i][1]
							+ " != null\">\r\n");
					if (i < r.length - 1)
						bw.write("                   " + ts[i][1] + " = #{"
								+ ts[i][1] + "},\r\n");
					else if (i == r.length - 1)
						bw.write("                   " + ts[i][1] + " = #{"
								+ ts[i][1] + "}\r\n");
					bw.write("             </if>\r\n");
				}
				bw.write("          </set>\r\n");
				bw.write("     where id = #{id}\r\n");
				bw.write(" </update>\r\n");

				// getById
				bw.write(" <select id=\"getById\" parameterType=\"java.lang.String\" resultMap=\"ResultMap\">\r\n");
				bw.write("     select\r\n");
				bw.write("        <include refid=\"Base_Column_List\" />\r\n");
				bw.write("        from " + tb + " \r\n");
				bw.write("        where id = #{id}\r\n");
				bw.write(" </select>\r\n");

				// getList
				bw.write(" <select id=\"getList\" parameterType=\"" + basePage
						+ ".entity." + bm + "\" resultMap=\"ResultMap\">\r\n");
				bw.write("       select\r\n");
				bw.write("        <include refid=\"Base_Column_List\" />\r\n");
				bw.write("        from " + tb + " \r\n");
				bw.write("        <where>\r\n");
				bw.write("             <if test=\"name != null and name != ''\">\r\n");
				bw.write("           		name like \"%\"#{name}\"%\"\r\n");
				bw.write("             </if>\r\n");
				bw.write("        </where>\r\n");
				bw.write(" </select>\r\n");

				// getPage
				bw.write(" <select id=\"getPage\" parameterType=\"" + basePage
						+ ".entity." + bm + "\" resultMap=\"ResultMap\">\r\n");
				bw.write("      select\r\n");
				bw.write("       <include refid=\"Base_Column_List\" />\r\n");
				bw.write("        from " + tb + " \r\n");
				bw.write("       <where>\r\n");
				bw.write("             <if test=\"name != null and name != ''\">\r\n");
				bw.write("         		  name like \"%\"#{name}\"%\"\r\n");
				bw.write("             </if>\r\n");
				bw.write("       </where>\r\n");
				bw.write("       LIMIT #{pageStart},#{pageEnd}\r\n");
				bw.write(" </select>\r\n");

				// getCount
				bw.write(" <select id=\"getCount\" parameterType=\"" + basePage
						+ ".entity." + bm + "\" resultType=\"int\">\r\n");
				bw.write("       select count(*) from " + tb + " \r\n");
				bw.write("         <where>\r\n");
				bw.write("             <if test=\"name != null and name != ''\">\r\n");
				bw.write("                name like \"%\"#{name}\"%\"\r\n");
				bw.write("             </if>\r\n");
				bw.write("         </where>\r\n");
				bw.write(" </select>\r\n");

				// custom
				bw.write(" <select id=\"select" + bt + "\" parameterType=\""
						+ basePage + ".entity." + bm
						+ "\" resultType=\"java.util.HashMap\">\r\n");
				bw.write("       select\r\n");
				bw.write("        <include refid=\"Base_Column_List\" />\r\n");
				bw.write("        from " + tb + " \r\n");
				bw.write("        <where>\r\n");
				bw.write("             <if test=\"name != null and name != ''\">\r\n");
				bw.write("           		name like \"%\"#{name}\"%\"\r\n");
				bw.write("             </if>\r\n");
				bw.write("        </where>\r\n");
				bw.write(" </select>\r\n");
				bw.write("</mapper>");
				bw.flush();

				// ================dao文件========================================================
				file = getFileDao(bt);
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write("package " + basePage + ".dao." + bt.toLowerCase()
						+ ";\r\n");
				bw.write("\r\n");
				bw.write("import java.util.List;\r\n");
				bw.write("import " + basePage + ".entity." + bm + ";\r\n");
				bw.write("\r\n");
				bw.write("/**\r\n");
				bw.write("* @author zj    default  \r\n");
				bw.write("* \r\n");
				bw.write("* @version 1.1\r\n");
				bw.write("*/\r\n");
				bw.write("\r\n");
				bw.write("public interface I" + bt + "Dao {\r\n");
				bw.write("  public List<" + bt + "> getList(String statement,"
						+ bt + " t);\r\n");
				bw.write("}");
				bw.flush();

				// ================daoImpl文件========================================================
				file = getFileDaoImpl(bt);
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write("package " + basePage + ".dao." + bt.toLowerCase()
						+ ".impl;\r\n");
				bw.write("\r\n");
				bw.write("import java.util.List;\r\n");
				bw.write("import org.springframework.stereotype.Repository;\r\n");
				bw.write("import " + basePage + ".dao." + bt.toLowerCase()
						+ ".I" + bt + "Dao;\r\n");
				bw.write("import " + commndPage + ".impl.BaseDaoImpl;\r\n");
				bw.write("import " + basePage + ".entity." + bm + ";\r\n");
				bw.write("\r\n");
				bw.write("/**\r\n");
				bw.write("* @author zj    default  \r\n");
				bw.write("* \r\n");
				bw.write("* @version 1.1\r\n");
				bw.write("*/\r\n");
				bw.write("\r\n");
				bw.write("@Repository\r\n");
				bw.write("public class " + bt + "DaoImpl extends BaseDaoImpl<"
						+ bt + ", Integer> implements I" + bt + "Dao{\r\n");
				bw.write("public List<" + bt + "> getList(String statement,"
						+ bt + " t){\r\n");
				bw.write("  return super.selectList(statement, t);\r\n");
				bw.write("  }\r\n");
				bw.write("}");
				bw.flush();

				// ================Service文件========================================================
				file = getService(bt);
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write("package " + basePage + ".service." + bt.toLowerCase()
						+ ";\r\n");
				bw.write("\r\n");
				bw.write("import java.util.List;\r\n");
				bw.write("import " + basePage + ".entity." + bm + ";\r\n");
				bw.write("\r\n");
				bw.write("/**\r\n");
				bw.write("* @author zj    default  \r\n");
				bw.write("* \r\n");
				bw.write("* @version 1.1\r\n");
				bw.write("*/\r\n");
				bw.write("\r\n");
				bw.write("public interface I" + bt + "Service {\r\n");
				bw.write("  public List<" + bt + "> select" + bt + "(" + bt
						+ " t);\r\n");
				bw.write("}");
				bw.flush();

				// ================ServiceImpl文件========================================================
				file = getServiceImpl(bt);
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write("package " + basePage + ".service." + bt.toLowerCase()
						+ ".impl;\r\n");
				bw.write("\r\n");
				bw.write("import java.util.List;\r\n");
				bw.write("import org.springframework.beans.factory.annotation.Autowired;\r\n");
				bw.write("import org.springframework.stereotype.Service;\r\n");
				bw.write("import " + basePage + ".dao." + bt.toLowerCase()
						+ ".impl." + bt + "DaoImpl;\r\n");
				bw.write("import " + basePage + ".service." + bt.toLowerCase()
						+ ".I" + bt + "Service;\r\n");
				bw.write("import " + commndPage + ".impl.BaseServiceImpl;\r\n");
				bw.write("import " + basePage + ".entity." + bm + ";\r\n");
				bw.write("\r\n");
				bw.write("/**\r\n");
				bw.write("* @author zj    default  \r\n");
				bw.write("* \r\n");
				bw.write("* @version 1.1\r\n");
				bw.write("*/\r\n");
				bw.write("\r\n");
				bw.write("@Service\r\n");
				bw.write("public class " + bt
						+ "ServiceImpl extends BaseServiceImpl<" + bt
						+ ", Integer> implements I" + bt + "Service{\r\n");
				bw.write("  @Autowired\r\n");
				bw.write("  private " + bt + "DaoImpl dao;\r\n");
				bw.write("\r\n");
				bw.write("  public List<" + bt + "> select" + bt + "(" + bt
						+ " t){\r\n");
				bw.write("       return dao.getList(\"select" + bt
						+ "\", t);\r\n");
				bw.write("  }\r\n");
				bw.write("}");
				bw.flush();

				// ================Controller文件========================================================
				file = getController(bt);
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write("package " + basePage + ".controller."
						+ tn.toLowerCase() + ";\r\n");
				bw.write("\r\n");
				bw.write("import java.util.List;\r\n");
				bw.write("import javax.servlet.http.HttpServletRequest;\r\n");
				bw.write("import javax.servlet.http.HttpServletResponse;\r\n");
				bw.write("import org.springframework.beans.factory.annotation.Autowired;\r\n");
				bw.write("import org.springframework.stereotype.Controller;\r\n");
				// bw.write("import org.springframework.web.bind.annotation.PathVariable;\r\n");
				bw.write("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
				bw.write("import org.springframework.web.bind.annotation.RequestMethod;\r\n");
				bw.write("import " + commndPage + ".impl.BaseController;\r\n");
				bw.write("import com.yichuang.kyjd.commnd.web.StaticSources;\r\n");
				bw.write("import net.sf.json.JSONObject;\r\n");
				bw.write("import " + basePage + ".service." + bt.toLowerCase()
						+ ".impl." + bt + "ServiceImpl;\r\n");
				bw.write("import " + basePage + ".entity." + bm + ";\r\n");
				// bw.write("import com.yichuang.kyjd.commnd.system.exception.BaseException;\r\n");
				// bw.write("import com.yichuang.kyjd.commnd.system.util.page.IGenericPage;\r\n");
				bw.write("\r\n");
				bw.write("/**\r\n");
				bw.write("* @author zj    default  test\r\n");
				bw.write("* \r\n");
				bw.write("* @version 1.1\r\n");
				bw.write("*/\r\n");
				bw.write("\r\n");
				bw.write("\r\n");
				bw.write("@Controller\r\n");
				bw.write("@RequestMapping(\"/" + osName + "/"
						+ bt.toLowerCase() + "\")\r\n");
				bw.write("public class " + bt
						+ "Controller extends BaseController<" + bt
						+ ", Integer> {\r\n");
				bw.write("\r\n");
				bw.write("  @Autowired\r\n");
				bw.write("  private " + bt + "ServiceImpl service;\r\n");
				bw.write("\r\n");
				bw.write("  @Autowired\r\n");
				bw.write("  public void setBaseService() {\r\n");
				bw.write("		setBaseService(service);\r\n");
				bw.write("	}\r\n");
				bw.write("\r\n");
				// bw.write("  /**\r\n");
				// bw.write("  * add \r\n");
				// bw.write("  * \r\n");
				// bw.write("  * @version 1.1\r\n");
				// bw.write("  */\r\n");
				// bw.write("  @RequestMapping(method = RequestMethod.POST)\r\n");
				// bw.write("  public void save(HttpServletRequest request, HttpServletResponse JsonResponse) {\r\n");
				// bw.write("  	try {\r\n");
				// bw.write("  		 String json = request.getParameter(StaticSources.JSONVO);\r\n");
				// bw.write("			 " + bt + " u = null;\r\n");
				// bw.write("			 if (json != null) {\r\n");
				// bw.write("			 	 u = (" + bt
				// + ") JSONObject.toBean(JSONObject.fromObject(json),"
				// + bt + ".class);\r\n");
				// bw.write("			 }\r\n");
				// bw.write("  		super.setSuccess(service.save(u));\r\n");
				// bw.write("  	}catch (Exception e) {\r\n");
				// bw.write("  		super.setError(e.getMessage());\r\n");
				// bw.write("      }\r\n");
				// bw.write("  	super.JsonResponse(JsonResponse);\r\n");
				// bw.write("  }\r\n");
				// bw.write("\r\n");
				// bw.write("\r\n");
				// bw.write("  /**\r\n");
				// bw.write("  * update \r\n");
				// bw.write("  * \r\n");
				// bw.write("  * @version 1.1\r\n");
				// bw.write("  */\r\n");
				// bw.write("  @RequestMapping(value = \"/{id}\", method = RequestMethod.POST)\r\n");
				// bw.write("  public void update(@PathVariable String id,HttpServletRequest request,HttpServletResponse JsonResponse) {\r\n");
				// bw.write("  	try {\r\n");
				// bw.write("  		 String json = request.getParameter(StaticSources.JSONVO);\r\n");
				// bw.write("			 " + bt + " u = null;\r\n");
				// bw.write("			 if (json != null) {\r\n");
				// bw.write("			 	 u = ("
				// + bt
				// // +
				// //
				// ") JSONObject.toBean(JSONObject.fromObject(new String(json.getBytes(\"ISO-8859-1\"), \"UTF-8\")),"
				// + ") JSONObject.toBean(JSONObject.fromObject(json),"
				// + bt + ".class);\r\n");
				// bw.write("			 }\r\n");
				// bw.write("  		super.setSuccess(service.update(u));\r\n");
				// // bw.write("  	} catch (BaseException e) {\r\n");
				// //
				// bw.write("  		super.setUnsupportedMediaType(e.getMessage());\r\n");
				// bw.write("  	}catch (Exception e) {\r\n");
				// bw.write("  		super.setError(e.getMessage());\r\n");
				// bw.write("      }\r\n");
				// bw.write("  	super.JsonResponse(JsonResponse);\r\n");
				// bw.write("  }\r\n");
				// bw.write("\r\n");
				// bw.write("\r\n");
				// bw.write("  /**\r\n");
				// bw.write("  * getbyId \r\n");
				// bw.write("  * \r\n");
				// bw.write("  * @version 1.1\r\n");
				// bw.write("  */\r\n");
				// bw.write("  @RequestMapping(value = \"/{id}\", method = RequestMethod.GET)\r\n");
				// bw.write("  public void getById(@PathVariable String id, HttpServletRequest request,HttpServletResponse JsonResponse) {\r\n");
				// bw.write("  	try {\r\n");
				// bw.write("  		super.setSuccess(service.getById(id));\r\n");
				// bw.write("  	} catch (BaseException e) {\r\n");
				// bw.write("  		super.setError(e.getMessage());\r\n");
				// bw.write("  	}\r\n");
				// bw.write("  	super.JsonResponse(JsonResponse);\r\n");
				// bw.write("  }\r\n");
				// bw.write("\r\n");
				// bw.write("\r\n");
				// bw.write("  /**\r\n");
				// bw.write("  * deletes \r\n");
				// bw.write("  * \r\n");
				// bw.write("  * @version 1.1\r\n");
				// bw.write("  */\r\n");
				// bw.write("  @RequestMapping(value = \"/delete/{ids}\", method = RequestMethod.POST)\r\n");
				// bw.write("  public void deleteByIds(@PathVariable String ids, HttpServletRequest request,HttpServletResponse JsonResponse) {\r\n");
				// bw.write("  	try {\r\n");
				// bw.write("  		 String[] str = ids.split(\",\");\r\n");
				// bw.write("  		 Integer[] id = new Integer[str.length];\r\n");
				// bw.write("  		 for (int i = 0; i < str.length; i++) {\r\n");
				// bw.write("  		 	id[i] = Integer.parseInt(str[i]);\r\n");
				// bw.write("  		 }\r\n");
				// bw.write("  		 super.setSuccess(service.deleteByIds(id));\r\n");
				// bw.write("  	} catch (BaseException e) {\r\n");
				// bw.write("  		super.setError(e.getMessage());\r\n");
				// bw.write("  	}\r\n");
				// bw.write("  	super.JsonResponse(JsonResponse);\r\n");
				// bw.write("  }\r\n");
				// bw.write("\r\n");
				// bw.write("\r\n");
				// bw.write("  /**\r\n");
				// bw.write("  * getList \r\n");
				// bw.write("  * \r\n");
				// bw.write("  * @version 1.1\r\n");
				// bw.write("  */\r\n");
				// bw.write("  @RequestMapping(method = RequestMethod.GET)\r\n");
				// bw.write("  public void getList(HttpServletRequest request, HttpServletResponse JsonResponse) {\r\n");
				// bw.write("  	try {\r\n");
				// bw.write("  		 String json = request.getParameter(StaticSources.JSONVO);\r\n");
				// bw.write("			 " + bt + " u = null;\r\n");
				// bw.write("			 if (json != null) {\r\n");
				// bw.write("			 	 u = (" + bt
				// // +
				// //
				// ") JSONObject.toBean(JSONObject.fromObject(new String(json.getBytes(\"ISO-8859-1\"), \"UTF-8\")),"
				// + ") JSONObject.toBean(JSONObject.fromObject(json),"
				// + bt + ".class);\r\n");
				// bw.write("			 }\r\n");
				// bw.write("  		 super.setSuccess(service.getList(u));\r\n");
				// // bw.write("  	} catch (BaseException e) {\r\n");
				// //
				// bw.write("  		super.setUnsupportedMediaType(e.getMessage());\r\n");
				// bw.write("  	}catch (Exception e) {\r\n");
				// bw.write("  		super.setError(e.getMessage());\r\n");
				// bw.write("      }\r\n");
				// bw.write("  	super.JsonResponse(JsonResponse);\r\n");
				// bw.write("  }\r\n");
				// bw.write("\r\n");
				// bw.write("\r\n");
				// bw.write("  /**\r\n");
				// bw.write("  * getPage \r\n");
				// bw.write("  * \r\n");
				// bw.write("  * @version 1.1\r\n");
				// bw.write("  */\r\n");
				// bw.write("  @RequestMapping(value = \"/{pageNo}/{pageSize}\", method = RequestMethod.GET)\r\n");
				// bw.write("  public void getPage(@PathVariable Integer pageNo,@PathVariable Integer pageSize,HttpServletRequest request, HttpServletResponse JsonResponse) {\r\n");
				// bw.write("  	try {\r\n");
				// bw.write("  		 String json = request.getParameter(StaticSources.JSONVO);\r\n");
				// bw.write("			 " + bt + " u = null;\r\n");
				// bw.write("			 if (json != null) {\r\n");
				// bw.write("			 	 u = (" + bt
				// // +
				// //
				// ") JSONObject.toBean(JSONObject.fromObject(new String(json.getBytes(\"ISO-8859-1\"), \"UTF-8\")),"
				// + ") JSONObject.toBean(JSONObject.fromObject(json),"
				// + bt + ".class);\r\n");
				// bw.write("			 }\r\n");
				// bw.write("  		 IGenericPage<"
				// + bt
				// +
				// "> list = service.getPage(u, pageNo, pageSize,null, null);\r\n");
				// bw.write("  		 super.setSuccess(list);\r\n");
				// // bw.write("  	} catch (BaseException e) {\r\n");
				// //
				// bw.write("  		super.setUnsupportedMediaType(e.getMessage());\r\n");
				// bw.write("  	}catch (Exception e) {\r\n");
				// bw.write("  		super.setError(e.getMessage());\r\n");
				// bw.write("      }\r\n");
				// bw.write("  	super.JsonResponse(JsonResponse);\r\n");
				// bw.write("  }\r\n");
				// bw.write("\r\n");
				bw.write("\r\n");
				bw.write("  /**\r\n");
				bw.write("  * custom \r\n");
				bw.write("  * \r\n");
				bw.write("  * @version 1.1\r\n");
				bw.write("  */\r\n");
				bw.write("  @RequestMapping(value = \"/param1\", method = RequestMethod.GET)\r\n");
				bw.write("  public void customSelect(HttpServletRequest request,HttpServletResponse response) {\r\n");
				bw.write("  	try {\r\n");
				bw.write("  		 String json = request.getParameter(StaticSources.JSONVO);\r\n");
				bw.write("			 " + bt + " u = null;\r\n");
				bw.write("			 if (json != null) {\r\n");
				bw.write("			 	 u = ("
						+ bt
						// +
						// ") JSONObject.toBean(JSONObject.fromObject(new String(json.getBytes(\"ISO-8859-1\"), \"UTF-8\")),"
						+ ") JSONObject.toBean(JSONObject.fromObject(json),"
						+ bt + ".class);\r\n");
				bw.write("			 }\r\n");
				bw.write("  		 List<" + bt + "> list = service.select" + bt
						+ "(u);\r\n");
				bw.write("  		 super.setSuccess(list);\r\n");
				// bw.write("  	} catch (BaseException e) {\r\n");
				// bw.write("  		super.setUnsupportedMediaType(e.getMessage());\r\n");
				bw.write("  	}catch (Exception e) {\r\n");
				bw.write("  		super.setError(e.getMessage());\r\n");
				bw.write("      }\r\n");
				bw.write("  	super.ResponseJson(response);\r\n");
				bw.write("  }\r\n");
				bw.write("}\r\n");
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @param names
	 * @return
	 */
	public List<Data> getListsByName(String names) {
		Set<Class<?>> classes = getClassesByPage(classesByPage);
		Iterator<Class<?>> it = classes.iterator();
		Data data2 = null;
		List<Data> list2 = new ArrayList<Data>();
		while (it.hasNext()) {
			Class<?> classs = it.next();
			String name = classs.getName();
			name = name.substring(name.lastIndexOf(".") + 1);
			name = name.replace("Controller", "").toLowerCase();
			if (name.equals(names)) {
				Method[] methods = classs.getMethods();
				for (Method method : methods) {
					data2 = new Data();
					String methodName = method.getName();
					if (!methodName.equals("ResponseJson")
							&& !methodName.equals("getResultToJSON")
							&& !methodName.equals("setError")
							&& !methodName.equals("setSuccess")
							&& !methodName.equals("setNoLogin")
							&& !methodName.equals("wait")
							&& !methodName.equals("hashCode")
							&& !methodName.equals("getClass")
							&& !methodName.equals("equals")
							&& !methodName.equals("toString")
							&& !methodName.equals("notify")
							&& !methodName.equals("notifyAll")
							&& !methodName.equals("getResultToString")
							&& !methodName.equals("ResponseString")
							&& !methodName.equals("setBaseService")
							&& !methodName.equals("setLoginUserError")
							&& !methodName.equals("setLoginPwdError")) {
						data2.setMethodName(methodName);
						method.getParameterAnnotations();
						Annotation[] ats = method.getAnnotations();
						for (Annotation at : ats) {
							String str = at.toString();
							if (str.indexOf("value") > 0
									&& str.indexOf("params") > 0) {
								str = str.substring(str.indexOf("value"),
										str.indexOf("params") - 1);
								String[] urls = str.split(",");
								for (String url : urls) {
									int index = url.indexOf("=");
									String key = url.substring(0, index);
									String value = url.substring(index,
											url.length());
									value = value.replace("[", "");
									value = value.replace("]", "");
									value = value.replace("=", "");
									if (key.equals("value")) {
										data2.setUrl("/" + projectName + "/"
												+ osName + "/" + name + value);
									} else if (key.equals(" method")) {
										data2.setMethod(value);
									}
								}
							}

						}
						list2.add(data2);
					}
				}
			}
		}
		return list2;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	public List<Data> getLists() {
		Set<Class<?>> classes = getClassesByPage(classesByPage);
		Iterator<Class<?>> it = classes.iterator();
		Data data = null;
		List<Data> list = new ArrayList<Data>();
		Data data2 = null;
		List<Data> list2;
		while (it.hasNext()) {
			data = new Data();
			Class<?> classs = it.next();
			String name = classs.getName();
			name = name.substring(name.lastIndexOf(".") + 1);
			name = name.replace("Controller", "").toLowerCase();
			data.setName(name);
			data.setUrl("/rest/" + name);
			Method[] methods = classs.getMethods();
			list2 = new ArrayList<Data>();
			for (Method method : methods) {
				data2 = new Data();
				String methodName = method.getName();
				if (!methodName.equals("JsonResponse")
						&& !methodName.equals("getResultToJSON")
						&& !methodName.equals("setError")
						&& !methodName.equals("setSuccess")
						&& !methodName.equals("wait")
						&& !methodName.equals("setNoLogin")
						&& !methodName.equals("hashCode")
						&& !methodName.equals("getClass")
						&& !methodName.equals("equals")
						&& !methodName.equals("toString")
						&& !methodName.equals("notify")
						&& !methodName.equals("notifyAll")
						&& !methodName.equals("setBaseService")
						&& !methodName.equals("setLoginUserError")
						&& !methodName.equals("setLoginPwdError")) {
					data2.setMethodName(methodName);
					method.getParameterAnnotations();
					Annotation[] ats = method.getAnnotations();
					for (Annotation at : ats) {
						String str = at.toString();
						str = str.substring(str.indexOf("value"),
								str.indexOf("params") - 1);
						String[] urls = str.split(",");
						for (String url : urls) {
							int index = url.indexOf("=");
							String key = url.substring(0, index);
							String value = url.substring(index, url.length());
							value = value.replace("[", "");
							value = value.replace("]", "");
							value = value.replace("=", "");
							if (key.equals("value")) {
								data2.setUrl("/" + projectName + "/" + osName
										+ "/" + name + value);
							} else if (key.equals(" method")) {
								data2.setMethod(value);
							}
						}

					}
					list2.add(data2);
				}
			}
			data.setList(list2);
			list.add(data);
		}
		return list;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClassesByPage(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection())
								.getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx)
											.replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class")
											&& !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(
												packageName.length() + 1,
												name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName
													+ "."
													+ className.toLowerCase()
													+ '.' + className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Thread.currentThread().getContextClassLoader()
							.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
