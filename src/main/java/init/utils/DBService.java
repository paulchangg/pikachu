package init.utils;

public class DBService {
	public  static final String host = "127.0.0.1";
	public  static final String DB_MYSQL = "MYSQL";
	public  static final String DB_SQLSERVER = "SQLSERVER";

	public  static final String DB_TYPE = DB_MYSQL;

	private static final String DBURL_SQLServer = "jdbc:sqlserver://" + host + ":1433;databaseName=JSPDB";
	public  static final String USERID_SQLServer = "sa";
	public  static final String PSWD_SQLServer = "sa123456";

	public  static final String nameMs = "java:comp/env/jdbc/BookDataMsSQL";
	public  static final String nameMy = "java:comp/env/jdbc/BookDataMySQL";
	
	public  static String JNDI_DB_NAME = (DB_TYPE.equals(DB_MYSQL)? nameMy : nameMs);
	
	static {
		if (JNDI_DB_NAME.equals(DB_MYSQL)) {
			JNDI_DB_NAME = nameMy;
		} else if (JNDI_DB_NAME.equals(DB_SQLSERVER)) {
			JNDI_DB_NAME = nameMs;
		}
	}
	
	private static final String DBURL_MySQL = "jdbc:mysql://" + host
			+ ":3306/jspdb?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei&allowPublicKeyRetrieval=true";
	public static final String USERID_MySQL = "root";
	public static final String PSWD_MySQL = "22651330";

	private static final String DROP_members_MySQL = "DROP Table IF EXISTS members ";
	

	private static final String DROP_cards_MySQL = "DROP Table IF EXISTS cards ";
	

	private static final String DROP_product_MySQL = "DROP TABLE IF EXISTS product";
	

	private static final String DROP_order_MySQL = "DROP Table IF EXISTS orders";
	

	private static final String DROP_orderitem_MySQL = "DROP TABLE IF EXISTS orderitem";


	private static final String DROP_holdcards_MySQL = "DROP TABLE IF EXISTS holdcards";

	
	private static final String DROP_forum_MySQL = "DROP TABLE IF EXISTS forum;";

	
	private static final String DROP_launch_activity_MySQL = "DROP TABLE IF EXISTS launch_activity;";

	
	private static final String DROP_responser_MySQL = "DROP TABLE IF EXISTS responser;";
	
	private static final String DROP_pair_MySQL = "DROP TABLE IF EXISTS pair;";
	
	
	private static final String CREATE_members_MySQL = " CREATE TABLE members " 
			+ "(m_id VARCHAR(100) NOT NULL,"
			+ " m_password VARCHAR(100) NOT NULL,"
			+ " name VARCHAR(100), " 
			+ " phone_num VARCHAR(100), "
			+ " m_mail VARCHAR(100), " 
			+ " nickname VARCHAR(100), "
			+ " birthday DATE, " 
			+ " gender VARCHAR(100), "
			+ " m_img BLOB, " 
			+ " income INT(11), "
			+ " city VARCHAR(100), " 
			+ " education VARCHAR(100), "
			+ " CONSTRAINT members_m_id_PK PRIMARY KEY(m_id) " 
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";
	
	private static final String CREATE_cards_MySQL = " CREATE TABLE cards " 
			+ " (c_id INT(11) NOT NULL AUTO_INCREMENT , "
			+ " c_name 			VARCHAR(30), " 
			+ " bank 			VARCHAR(30), "
			+ " annlfee 		VARCHAR(30), " 
			+ " c_img 			LONGBLOB, "
			+ " fcb 			DECIMAL(10,4), " 
			+ " dcb 			DECIMAL(10,4), "
			+ " fcb_n 			VARCHAR(255), " 
			+ " dcb_n 			VARCHAR(255), " 
			+ " fma 			DECIMAL(10,4), "
			+ " dma 			DECIMAL(10,4), "
			+ " fma_n 			VARCHAR(255),  " 
			+ " dma_n 			VARCHAR(255),  " 
			+ " refuel_cb 		DECIMAL(10,4),  " 
			+ " refuel_dis 		DECIMAL(10,2),  " 
			+ " refuel_cb_n 	VARCHAR(255),  " 
			+ " refuel_dis_n 	VARCHAR(255),  " 
			+ " wkdmoviedis 	INT(11),  " 
			+ " moviedis 		INT(11),  " 
			+ " wkdmoviedis_n 	VARCHAR(255),  " 
			+ " moviedis_n 		VARCHAR(255),   " 
			+ " ditigalfb 		VARCHAR(30),  " 
			+ " onlinefb 		DECIMAL(10,4),  " 
			+ " ditigalfb_n 	VARCHAR(255),  " 
			+ " onlinefb_n 		VARCHAR(255), " 
			+ " zeroins 		VARCHAR(30),  " 
			+ " minins 			VARCHAR(30),  " 
			+ " zeroins_n 		VARCHAR(255),  " 
			+ " minins_n 		VARCHAR(255),  " 
			+ " mileagefb 		VARCHAR(30),  " 
			+ " cashfb 			VARCHAR(30),  " 
			+ " mileagefb_n 	VARCHAR(255),  " 
			+ " cashfb_n 		VARCHAR(255),  " 
			+ " annual_income 	INT(11),  " 
			+ " c_type 			VARCHAR(30),  " 
			+ " CONSTRAINT cards_c_id_PK PRIMARY KEY(c_id)  " 			
			+ " )  ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci  ";
	
	private static final String CREATE_product_MySQL = "Create TABLE product " 
			+ "(p_id INT(11) NOT NULL AUTO_INCREMENT, "
			+ " p_name 		VARCHAR(30), " 
			+ " p_desc 		LONGTEXT, " 
			+ " price 		DECIMAL(10,2), "
			+ " stock 		INT(11), " 
			+ " p_img 		LONGBLOB, " 
			+ " p_bns 		INT(11), "
			+ " CONSTRAINT product_p_id_PK PRIMARY KEY(p_id) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";

	private static final String CREATE_order_MySQL = "Create TABLE orders "
			+ "(ordid           INT(11) NOT NULL , " 
			+ " m_id          	VARCHAR(100), "
			+ " orderdate       DATETIME, " 
			+ " total     		DECIMAL(8,2), " 
			+ " CONSTRAINT orders_ordid_PK PRIMARY KEY(ordid),"
			+ " CONSTRAINT orders_m_id_FK FOREIGN KEY(m_id) REFERENCES  members(m_id)"
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";

	private static final String CREATE_orderitem_MySQL = "Create Table orderitem "
			+ "(itemid  		INT(11) NOT NULL AUTO_INCREMENT, " 
			+ " ordid         	INT(11), "
			+ " p_id		    INT(11), " 
			+ " price			DECIMAL(8,2), "
			+ " qty             DECIMAL(8,2), " 
			+ " itemtotal    	DECIMAL(8,2), " 
			+ " CONSTRAINT order_orderitem_PK PRIMARY KEY(itemid), "
			+ "CONSTRAINT orderitem_ordid_FK FOREIGN KEY(ordid) REFERENCES  orders(ordid),"
			+ "CONSTRAINT orderitem_p_id_FK FOREIGN KEY(p_id) REFERENCES  product(p_id)"
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";

	private static final String CREATE_holdcards_MySQL = "Create Table holdcards "
			+ "(hc_id 			INT(11)NOT NULL AUTO_INCREMENT , " 
			+ " card_id 		INT(11) , "
			+ " m_id 		VARCHAR(100) , " 
			+ " CONSTRAINT holdcards_hc_id_PK PRIMARY KEY(hc_id),"
			+ "	CONSTRAINT holdcards_m_id_FK FOREIGN KEY(m_id) REFERENCES  members(m_id),"
			+ "	CONSTRAINT holdcards_card_id_FK FOREIGN KEY(card_id) REFERENCES  cards(c_id)" 
			+ " )ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";

	private static final String CREATE_forum_MySQL =
			  " CREATE TABLE forum "
			+ "( f_id      		INT(11) NOT NULL  AUTO_INCREMENT, " 
			+ "  fname      	VARCHAR(255), " 
			+ "	 startdate    	DATETIME, "
			+ "  owner_m_id     INT(11), " 
			+ "  CONSTRAINT forum_f_id_PK PRIMARY KEY(f_id) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci  ";

	private static final String CREATE_launch_activity_MySQL = "CREATE TABLE launch_activity "
			+ "(article_Id          INT(10) NOT NULL AUTO_INCREMENT , " 
			+ " article_m_id        VARCHAR(100) NOT NULL, "
			+ " f_id      			INT(11) NOT NULL, " 
			+ " article_title       LONGTEXT,"
			+ "	article_content  	LONGTEXT,"
			+ "	subject     		VARCHAR(255),"
			+ " Location          	VARCHAR(255),"
			+ "post_Time         	DATETIME,"
			+ "updateTime       	DATETIME,"
			+ "endTime           	DATETIME,"
			+ "popularity        	INT(10),"
			+ "CONSTRAINT launch_activity_article_Id_PK PRIMARY KEY(article_Id)," 
			+ "CONSTRAINT launch_activity_Aticle_m_id_FK FOREIGN KEY(article_m_id) REFERENCES  members(m_id),"  
			+ "CONSTRAINT launch_activity_f_id_FK FOREIGN KEY(f_id ) REFERENCES forum(f_id) "
			+ ") ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci ";


	private static final String CREATE_responser_MySQL = "CREATE TABLE responser " 
			+ "(res_id         		INT(10) NOT NULL AUTO_INCREMENT, "
			+ " res_m_id      		VARCHAR(100) NOT NULL, " 
			+ " article_Id       	INT(10) NOT NULL, " 
			+ " updateTime   		DATETIME, "
			+ " res_content   		LONGTEXT, "
			+ "CONSTRAINT responser_res_id_PK PRIMARY KEY(res_id),"
			+ "CONSTRAINT responser_Aticle_Id_FK FOREIGN KEY(article_Id) REFERENCES launch_activity(article_Id)," 
			+ "CONSTRAINT responser_res_m_id_FK FOREIGN KEY(res_m_id) REFERENCES members(m_id)" 
			+ " )ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";
	
	private static final String CREATE_pair_MySQL = "CREATE TABLE pair " 
			+ "(p_id         		INT(10) NOT NULL AUTO_INCREMENT, "
			+ " pair_id      		VARCHAR(100) NOT NULL, " 
			+ " m_Id       			VARCHAR(100) NOT NULL, " 
			+ "CONSTRAINT pair_p_id_PK PRIMARY KEY(p_id),"
			+ "CONSTRAINT pair_res_m_id_FK FOREIGN KEY(m_Id) REFERENCES members(m_id)" 
			+ " )ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";


	
	//1.members表格
	public static String getDropMembers() {
		String drop = null;		
		drop = DROP_members_MySQL;			
		return drop;
	}
	
	public static String getCreateMembers() {
		String create = null;
		create = CREATE_members_MySQL;
		return create;
	}
	//2.card表格
	public static String getDropCards() {
		String drop = null;
		drop = DROP_cards_MySQL;
		return drop;
	}
	public static String getCreateCards() {
		String create = null;
		create = CREATE_cards_MySQL;
		return create;
	}
	
	//3.product表格
	public static String getCreateProduct() {
		String create = null;		
		create = CREATE_product_MySQL;		
		return create;
	}

	public static String getDropProduct() {
		String drop = null;
		drop = DROP_product_MySQL;
		return drop;
	}
	
	// 4. order表格
	public static String getDropOrder() {
		String drop = null;		
		drop = DROP_order_MySQL;			
		return drop;
	}
	
	public static String getCreateOrder() {
		String create = null;
		create = CREATE_order_MySQL;
		return create;
	}
	//5. orderitem表格
	public static String getCreateOrderItem() {
		String create = null;
		create = CREATE_orderitem_MySQL;
		return create;
	}
	
	public static String getDropOrderItem() {
		String drop = null;
		drop = DROP_orderitem_MySQL;
		return drop;
	}
	//6.holdcards表格
	public static String getCreateHoldcards() {
		String create = null;
		create = CREATE_holdcards_MySQL;
		return create;
	}
	
	public static String getDropHoldcards() {
		String drop = null;
		drop = DROP_holdcards_MySQL;
		return drop;
	}
	//7 forum表格
	public static String getCreateForum() {
		String create = null;
		create = CREATE_forum_MySQL;
		return create;
	}
	
	public static String getDropForum() {
		String drop = null;
		drop = DROP_forum_MySQL;
		return drop;
	}
	//8 launch_activity表格
	public static String getCreateLlaunchActivity() {
		String create = null;
		create = CREATE_launch_activity_MySQL;
		return create;
	}
	
	public static String getDropLaunchActivity() {
		String drop = null;
		drop = DROP_launch_activity_MySQL;
		return drop;
	}
	//9.responser表格
	public static String getCreateResponser() {
		String create = null;
		create = CREATE_responser_MySQL;
		return create;
	}
	
	public static String getDropResponser() {
		String drop = null;
		drop = DROP_responser_MySQL;
		return drop;
	}
	
	//10.pair表格
		public static String getCreatePair() {
			String create = null;
			create = CREATE_pair_MySQL;
			return create;
		}
		
		public static String getDropPair() {
			String drop = null;
			drop = DROP_pair_MySQL;
			return drop;
		}
	
	

	public static String getDbUrl() {
		String url = null;
		url = DBURL_MySQL;
		System.out.println("url=" + url);
		return url;
	}

	public static String getUser() {
		String user = null;
		user = USERID_MySQL;		
		return user;
	}

	public static String getPassword() {
		String pswd = null;
		pswd = PSWD_MySQL;
		return pswd;
	}

}
