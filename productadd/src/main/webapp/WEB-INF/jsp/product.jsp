<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
        <meta charset="UTF-8">
        <title>京东-发布商品</title>
        <link href="css/common.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-labelauty.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="css/jd.css"/>
        <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
        <script src="js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
        <script src="js/common_js.js" type="text/javascript"></script>
        <script src="js/footer.js" type="text/javascript"></script>
        <script src="kindeditor/kindeditor-min.js" type="text/javascript"></script>
        <script src="js/jquery-labelauty.js" type="text/javascript"></script>
        <script>
            KindEditor.ready(function (K) {
                var obj;
                obj = K.create('#disc', {
                    uploadJson: 'uploadFile',//文件上传到指定的Controller的RequestMapping(/"uploadFile")
                    filePostName: 'mf',//接收文件的方法参数列表名称
                    dir: 'image'//上传的文件类型
                });
                //下面的处理能回显上传成功之后的图片到多行文本框中
                obj.sync();
            });
        </script>
    </head>
<body>
<!--头部log-->
<div class="heart">
    <div class="log">
        <img src="images/logo-201305-b.png"/>
        欢迎${user.userName}归来!
    </div>

</div>
<!--提示-->
<div class="nav">
</div>
<!--提示-->
<!--发布商品样式-->
<div class="Inside_pages clearfix">
    <div class="left_style">
        <!--列表-->
        <div class="menu_style">
            <ul class="menu_list">
                <li><em></em><a href="店铺专区.html">店铺专区</a></li>
                <li class="on"><em></em><a href="商品专区.html">商品专区</a></li>
                <li><em></em><a href="#">订单专区</a></li>
                <li><em></em><a href="#">发货管理</a></li>
                <li><em></em><a href="#">收款账户</a></li>
                <li><em></em><a href="#">我的报表</a></li>
            </ul>
        </div>
    </div>
    <div class="right_style">
        <!--内容详细-->
        <div class="title_style"><em></em>发布商品</div>
        <div class="content_style">
            <div class="Release_product_style">
                <form action="addProduct" method="post">
                    <table cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td class="label">店铺/公司名称：</td>
                            <td>奶茶妹家的旗舰店</td>
                        </tr>
                        <tr>
                            <td class="label">商品标题：</td>
                            <td><input name="title" type="text" class="addtext"
                                       style="width:500px;"/></td>
                        </tr>
                        <tr>
                            <td class="label">设置商品分类：</td>
                            <td>
                                <span>一级分类：</span><select name="group1" size="1">
                                <option value="0" selected="selected">一级分类</option>
                                <option value="1">3C数码</option>
                                <option value="2">电脑/办公</option>
                                <option value="3">家具/家装</option>
                                <option value="4">男装/女装</option>
                                <option value="5">食品区</option>
                                <option value="6">其他</option>
                            </select>
                                <span>二级分类：</span><select name="group2" size="1">
                                <option value="0">二级分类</option>
                                <option value="11">手机</option>
                                <option value="22">数码</option>
                                <option value="33">数码配件</option>
                                <option value="44">电脑整机</option>
                                <option value="55">电脑外设配件</option>
                                <option value="66">饮料类</option>
                                <option value="77">农产品</option>
                                <option value="88">其他</option>
                            </select>
                                <span>三级分类：</span>
                                <select name="group3" size="1">
                                    <option value="0">三级分类</option>
                                    <option value="111">智能手机</option>
                                    <option value="222">老人手机</option>
                                    <option value="333">台式机</option>
                                    <option value="444">笔记本</option>
                                    <option value="555">商务装</option>
                                    <option value="666">休闲装</option>
                                    <option value="777">酒水/饮料</option>
                                    <option value="888">生鲜/水果</option>
                                    <option value="999">其他</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">商品销售价格：</td>
                            <td><input name="price" type="text" class="addtext"
                                       style=" width:200px;"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="label">商品卖点介绍：</td>
                            <td>
                                <textarea name="sellpoint" cols="" rows="" placeholder="商品的卖点,该信息将出现在商品名称下方。"
                                          class="textarea"></textarea>
                            </td>
                        </tr>

                        <tr>
                            <td class="label">商品详细介绍：</td>
                            <td>
                                <textarea name="detail" id="disc" placeholder="这里写商品的描述" style="width: 800px;height: 300px">
                                </textarea>
                            </td>
                        </tr>

                    </table>
                    <a class="Next_btn">&nbsp;&nbsp;</a>
                    <input type="submit" value="提交新增商品" style="color:white;font-size: 18px;background-color: #E4393C;width: 220px;height: 40px;
                    position: relative;left: 390px;top: -40px;border: 1px red solid;"/>

                </form>
            </div>
        </div>
    </div>
</div>

<!--网站地图-->
<div class="fri-link-bg clearfix">
    <div class="fri-link">
        <div class="logo left margin-r20"><img src="images/fo-logo.jpg" width="152" height="81"/></div>
        <div class="left"><img src="images/qd.jpg" width="90" height="90"/>
            <p>扫描下载APP</p>
        </div>
        <div class="">
            <dl>
                <dt>新手上路</dt>
                <dd><a href="#">售后流程</a></dd>
                <dd><a href="#">购物流程</a></dd>
                <dd><a href="#">订购方式</a></dd>
                <dd><a href="#">隐私声明 </a></dd>
                <dd><a href="#">推荐分享说明 </a></dd>
            </dl>
            <dl>
                <dt>配送与支付</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a></dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>售后保障</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a></dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>支付方式</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a></dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a></dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a></dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a></dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
        </div>
    </div>
</div>
<!--网站地图END-->
<!--网站页脚-->
<div class="copyright">
    <div class="copyright-bg">
        <div class="hotline">为生活充电在线 <span>招商热线：****-********</span> 客服热线：400-******</div>
        <div class="hotline co-ph">
            <p>版权所有Copyright ©Teacher:Sailing</p>
            <p>*ICP备***************号 不良信息举报</p>
            <p>总机电话：****-*********/194/195/196 客服电话：4000****** 传 真：********

                E-mail:****@****.gov.cn</p>
        </div>
    </div>
</div>
</body>
</html>

