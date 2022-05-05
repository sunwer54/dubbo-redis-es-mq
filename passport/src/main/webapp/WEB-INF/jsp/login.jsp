<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
        <meta charset="utf-8" />
        <title>京东-欢迎登录</title>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
        <link rel="stylesheet" type="text/css" href="css/public.css"/>
    </head>
<body>
<!--头部log-->
<div class="heart">
    <div class="log">
        <img src="images/logo-201305-b.png"/>
        <b></b>
    </div>
    <div class="wz">
        <a href="#"></a>
        <b>登陆页面，调差问卷</b>
    </div>
</div>
<!--提示-->
<div class="nav">
</div>
<!--登录-->
<div class="login">
    <div class="login_warp">
        <div class="login_form">
            <div class="login_heard">
            </div>
            <div class="login_bo">
                <div class="left">扫码登录</div>
                <div class="right">账号登录</div>

            </div>
            <!--用户数据 提交-->
            <div class="form">
                <form action="loginUser" method="post">

                    <div class="username">
                        <div class="log_img1"></div>
                        <input type="text" name="userName" id="username" value="" placeholder="邮箱/用户名/登录手机" />
                    </div>
                    <div class="password">
                        <div class="log_img2"></div>
                        <input type="password" name="pwd" id="password" value="" placeholder="密码"/>
                    </div>
                    <div class="log_wz">
                        <p>忘记密码</p>
                    </div>
                    <div style="margin: auto;">
                        <p style="text-align: center;color: red">${fail}</p>
                    </div>
                    <input type="submit" value="登录" id="submit"/>
                </form>
            </div>
        </div>
    </div>

</div>

<!--底部-->
<div class="foot"></div>
</body>
</html>
