<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <head>
        <base href="<%=basePath%>">
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <link href="css/common.css" rel="stylesheet" type="text/css" />
            <link href="css/style.css" rel="stylesheet" type="text/css" />
            <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
            <script src="js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
            <script src="js/common_js.js" type="text/javascript"></script>
            <script src="js/footer.js" type="text/javascript"></script>
            <title>商品专区</title>
        </head>

<body>
<head>
    <div id="header_top">
        <div id="top">
            <div class="Inside_pages">
                <div class="Collection"><a href="#" class="green">请登录</a> <a href="#" class="green">免费注册</a></div>
                <div class="hd_top_manu clearfix">
                    <ul class="clearfix">
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="#">首页</a></li>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"> <a href="#">我的小充</a> </li>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="#">消息中心</a></li>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="#">商品分类</a></li>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="#">我的购物车<b>(23)</b></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="header"  class="header page_style">
            <div class="logo"><a href="index.html"><img src="images/logo.png" /></a></div>
            <!--结束图层-->
            <div class="Search">
                <div class="search_list">
                    <ul>
                        <li class="current"><a href="#">产品</a></li>
                        <li><a href="#">信息</a></li>
                    </ul>
                </div>
                <div class="clear search_cur">
                    <form action="searchProduct">
                    <input name="searchName" id="searchName" class="search_box" onkeydown="keyDownSearch()" type="text">
                    <input name="" type="submit" value="搜 索"  class="Search_btn"/>
                    </form>
                </div>
                <div class="clear hotword">热门搜索词：香醋&nbsp;&nbsp;&nbsp;茶叶&nbsp;&nbsp;&nbsp;草莓&nbsp;&nbsp;&nbsp;葡萄&nbsp;&nbsp;&nbsp;菜油</div>
            </div>
            <!--购物车样式-->
            <div class="hd_Shopping_list" id="Shopping_list">
                <div class="s_cart"><a href="#">我的购物车</a> <i class="ci-right">&gt;</i><i class="ci-count" id="shopping-amount">0</i></div>
                <div class="dorpdown-layer">
                    <div class="spacer"></div>
                    <!--<div class="prompt"></div><div class="nogoods"><b></b>购物车中还没有商品，赶紧选购吧！</div>-->
                    <ul class="p_s_list">
                        <li>
                            <div class="img"><img src="images/tianma.png"></div>
                            <div class="content"><p class="name"><a href="#">产品名称</a></p><p>颜色分类:紫花8255尺码:XL</p></div>
                            <div class="Operations">
                                <p class="Price">￥55.00</p>
                                <p><a href="#">删除</a></p></div>
                        </li>
                    </ul>
                    <div class="Shopping_style">
                        <div class="p-total">共<b>1</b>件商品　共计<strong>￥ 515.00</strong></div>
                        <a href="Shop_cart.html" title="去购物车结算" id="btn-payforgoods" class="Shopping">去购物车结算</a>
                    </div>
                </div>
            </div>
        </div>
        <!--菜单栏-->
        <div class="Navigation" id="Navigation">
            <ul class="Navigation_name">
                <li><a href="Home.html">首页</a></li>
                <li><span class="bg_muen"></span><a href="#">半小时生活圈</a></li>
                <li><a href="#">你身边的超市</a></li>
                <li><a href="#">预售专区</a><em class="hot_icon"></em></li>
                <li><a href="products_list.html">商城</a></li>
                <li><a href="#">好评商户</a></li>
                <li><a href="#">热销活动</a></li>
                <li><a href="Brands.html">联系我们</a></li>
            </ul>
        </div>
        <script>$("#Navigation").slide({titCell:".Navigation_name li",trigger:"click"});</script>
    </div>
</head>
<!--商品专区-->
<div class="Inside_pages clearfix">

    <!--内容-->
    <div class="right_style">
        <!--内容详细-->
        <div class="title_style"><em></em>商品专区</div>
        <div class="content_style">
            <div class="Products_area_style">

                <div class="Products_list_style">
                    <table>
                        <thead>
                        <tr class="title">
                            <td class="checkbox_style"><input name="" type="checkbox" value="" /></td>
                            <td class="title_name">商品标题</td>
                            <td class="inventory">商品库存</td>
                            <td class="status">商品状态</td>
                            <td class="operating">操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${products}" var="product">
                        <tr>
                            <td><input name="" type="checkbox" value="" /></td>
                            <td><a href="#">${product.title}</a></td>
                            <td>${product.price}</td>
                            <td>${product.status}</td>
                            <td class="operating_btn relative">
                                <div class="inventory_style">
                                    <input name="" type="text"  class="add_Number"/><input name="1"  type="submit" value="确认" class="confirm_btn" /> <a href="#" class="cancel"><em class="cancel-icon"></em></a>
                                </div>
                                <a href="http://localhost:9092/downPro/${product.pid}" class="szkc_btn">下架</a><a href="http://localhost:9092/upPro/${product.pid}" class="sj_btn" id="sj_btn">上架</a></td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="page_style">每页显示<select name="" size="1"> <option value="1">10</option></select>共3页 共28条，当前第1/3页 <a href="#">首页</a><a href="#">上一页</a><a href="#">下一页</a><a href="#">尾页</a></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--网站地图-->
<div class="fri-link-bg clearfix">
    <div class="fri-link">
        <div class="logo left margin-r20"><img src="images/fo-logo.jpg" width="152" height="81" /></div>
        <div class="left"><img src="images/qd.jpg" width="90"  height="90" />
            <p>扫描下载APP</p>
        </div>
        <div class="">
            <dl>
                <dt>新手上路</dt>
                <dd><a href="#">售后流程</a></dd>
                <dd><a href="#">购物流程</a></dd>
                <dd><a href="#">订购方式</a> </dd>
                <dd><a href="#">隐私声明 </a></dd>
                <dd><a href="#">推荐分享说明 </a></dd>
            </dl>
            <dl>
                <dt>配送与支付</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a> </dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>售后保障</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a> </dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>支付方式</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a> </dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a> </dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a> </dd>
                <dd><a href="#">常见问题 </a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="#">保险需求测试</a></dd>
                <dd><a href="#">专题及活动</a></dd>
                <dd><a href="#">挑选保险产品</a> </dd>
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
            <p>版权所有Copyright ©***************</p>
            <p>*ICP备***************号 不良信息举报</p>
            <p>总机电话：****-*********/194/195/196 客服电话：4000****** 传 真：********

                E-mail:****@****.gov.cn</p>
        </div>
    </div>
</div>
</body>
</html>

