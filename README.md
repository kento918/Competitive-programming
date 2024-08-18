# myblog system
#### 現在公開している自己学習で作成したサイトになります。
#### http://kentotechblog.com/ でサイトのほうには移動できます。
#### 更新頻度は低めになっていますが、躓いたところ、困ったところをまとめているサイトになります。

<details><summary>コードを使用したい場合</summary>
1.データベースについてはsql_sorce.sqlをご活用ください。
（テストしていないです。カラムは問題ないのでキー回り削除したら最悪大丈夫だと思います。）

2.src/main/resourcesにapplication.propertiesを作成してください。
中身は下記の通りして頂けるとよいと思います。mysqlを使用しています。

```ruby:application.properties
spring.application.name=mybapp
spring.datasource.url=jdbc:mysql://localhost:3306/blogapp
spring.datasource.username=名前
spring.datasource.password=パスワード
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

3.src/main/resources/configにmybatis-config.xmlを作成してください。
2と同様に中身は下記の通りです。

```ruby:mybatis-config.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configration SYSTEM "mybatis-3-config.dtd">
<configration>
	<properties>
		<property name="jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
		<property name="jdbc.url" value="jdbc:mysql://localhost:3306/blogapp" />
		<property name="jdbc.username" value="名前" />
		<property name="jdbc.password" value="パスワード" />
	</properties>
	<mappers>
	</mappers>
</configration>
```

</details>