1、resultType（属性）和resultMap（标签引用）的区别？

    resultType可以作为单表的简单映射
    resultMap 高级对象映射，可以指定数据库列名对应pojo对象的属性名的映射关系。多表关联时，一般要生成新的POJO对象，这时可以用resultMap。
    例如：
    <resultMap id="detailedBlogResultMap" type="Blog">
      <constructor>
        <idArg column="blog_id" javaType="int"/>
      </constructor>
      <result property="title" column="blog_title"/>
      <association property="author" javaType="Author">
        <result property="username" column="author_username"/>
      </association>
      <collection property="posts" ofType="Post">
        <id property="id" column="post_id"/>
        <result property="subject" column="post_subject"/>
        <association property="author" javaType="Author"/>
        <collection property="comments" ofType="Comment">
          <id property="id" column="comment_id"/>
        </collection>
        <discriminator javaType="int" column="draft">
          <case value="1" resultType="DraftPost"/>
        </discriminator>
      </collection>
    </resultMap>
2、collection和association的区别？

    association 关联标签 ,处理'有一个'标签，关联一般用在复杂的POJO对象中。需要指定目标属性名跟目标的javaType.
    两种方式加载关联
    1、嵌套Select查询 通过执行另外一个 SQL 映射语句来加载期望的复杂类型
    <resultMap id="blogResult" type="Blog">
      <association property="author" column="author_id" javaType="Author" select="selectAuthor"/>
    </resultMap>
   
    <select id="selectBlog" resultMap="blogResult">
      SELECT * FROM BLOG WHERE ID = #{id}
    </select>
    执行selectBlog后执行selectAuthor;
    
    2、嵌套结果映射
    <resultMap id="blogResult" type="Blog">
      <id property="id" column="blog_id" />
      <result property="title" column="blog_title"/>
      <association property="author" javaType="Author">
        <id property="id" column="author_id"/>
        <result property="username" column="author_username"/>
      </association>
    </resultMap>
    
    collection集合标签 处理'多个' 集合 
    <resultMap id="blogResult" type="Blog">
      <id property="id" column="blog_id" />
      <collection property="posts" ofType="Post">  posts是类型Post的ArrayList集合
        <id property="id" column="post_id"/>
        <result property="subject" column="post_subject"/>
      </collection>
    </resultMap>


3、Statement和PreparedStatement的区别？

    preparedStatement继承自Statement。
    preparedStarement会先预编译SQL，等待值执行,只编译一次；Statement每次只从都是重新编译SQL;
    preparedStatement可以替换预编译后的SQL的变量，
    例如 select * from user where id = ?;
    而statement只能
    st.executeQuery("select * from user where id="+xxx);
    xxx可以是任意符号,如替换成 1 or 1==1
    这样where条件就变成 where id = 1 or 1==1条件恒成立，甚至更过分可以直接删掉表。如将xxx替换成 '1 or 1==1 ;drop user'
    

    
