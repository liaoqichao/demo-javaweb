<%@ page language="java" import="java.net.*" pageEncoding="utf-8"%><%@   page import="java.sql.*"%>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@   page import="java.util.*"%><%@   page import="java.text.*"%><%@   page import="java.io.*"%><%
   Class.forName("com.mysql.jdbc.Driver").newInstance();
	String url = "jdbc:mysql://localhost:3306/oasys";
	String username = "root";
	String password = "123456";
   //其中mysql为你数据库的名字，user为你连接数据库的用户，password为你连接数据库用户的密码，可自己改    
   Connection conn = DriverManager.getConnection(url, username, password);
   Statement stmt=null;
   ResultSet rs = null;
   String documentNo=request.getParameter("documentNo");
  // String documentNo="6B3C8F7AB8E940F297DC266C589E2500_test.txt";
   System.out.println(documentNo);

    if(documentNo.equals("")){
 		out.println("没有你要下载的文件"); 
   } 
  else{ System.out.println(documentNo);
	  String sql = "select documentHeader,document  from documenttable  where documentNo='"+documentNo+"'";
	  System.out.println(sql);
	   try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(sql);
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   
	   try {
		   if (rs.next()) {
			   System.out.println(documentNo);
	  			response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(rs.getString("documentHeader"),"UTF-8"));  
	 			response.setHeader("Connection",  "close"); 
	  			response.setHeader("Content-Type",  "application/octet-stream");  
	   
	  			ServletOutputStream sout = response.getOutputStream();
	  			InputStream  in = rs.getBinaryStream("document");
	  			byte b[] = new byte[1024*8];
	  			for(int i=in.read(b);i!=-1;){
	  				sout.write(b);
	  				i=in.read(b);
	  			}
	     		sout.flush();
	     		sout.close();
	     		in.close();
	    	}
	   } catch (Exception e) {
		   System.out.println(e);
	   }finally 
	     { 
	         if(rs != null) 
	         { 
	             rs.close(); 
	         } 

	         if(stmt != null) 
	         { 
	             stmt.close(); 
	         } 
	         if(conn != null) 
	         { 
	             conn.close(); 
	         } 
	     } 
   }
  
  %>
