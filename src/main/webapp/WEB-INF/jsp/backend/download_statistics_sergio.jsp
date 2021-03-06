<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <form id="dataFilter" style="margin-bottom: 20px;">
            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="form-group">
                        <label for="dateIni">Initial date</label>
                        <input type="text" name="dateIni" class="form-control hasDatepicker" id="dateIni" value="<s:property value="dateIni" />">
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="form-group">
                        <label for="dateEnd">Ending date</label>
                        <input type="text" name="dateEnd" class="form-control hasDatepicker" id="dateEnd" value="<s:property value="dateIni" />">
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="form-group">
                        <label for="otaId">OTA(s)</label>
                        <select name="otaId" id="otaId" class="form-control">
                            <s:iterator value="otas">
                                <option value="<s:property value="id"/>"<s:if test="%{id == otaId}"> selected</s:if>><s:property value="name" /></option>
                            </s:iterator>
                        </select>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="form-group">
                        <label for="hotelId">Hotel(s)</label>
                        <select name="hotelId" id="hotelId" class="form-control">
                            <s:iterator value="hotels">
                                <option value="<s:property value="id"/>"<s:if test="%{id == hotelId}"> selected</s:if>><s:property value="name" /></option>
                            </s:iterator>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6">
                    <button type="submit" class="btn btn-success">Generate</button>
                </div>
            </div>
        </form>

        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <b>Price in OTAs vs Direct Price</b>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div style="width: 100%">
                            <canvas id="graph1" ></canvas>
                        </div>
                    </div>
                    <div class="panel-footer clearfix">
                        <div class="pull-right"><!--
                            <a href="#" class="btn btn-primary">Learn More</a>
                            <a href="#" class="btn btn-default">Go Back</a>
                        --></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <b>Price in OTAs vs Direct Price (equal =100)   by hotel </b>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div style="width: 100%">
                            <canvas id="graph2" ></canvas>
                        </div>
                    </div>
                    <div class="panel-footer clearfix">
                        <div class="pull-right"><!--
                            <a href="#" class="btn btn-primary">Learn More</a>
                            <a href="#" class="btn btn-default">Go Back</a>
                        --></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <b>Price in OTA &lt; Direct Price </b>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div style="width: 100%">
                            <canvas id="graph3" ></canvas>
                        </div>
                    </div>
                    <div class="panel-footer clearfix">
                        <div class="pull-right"><!--
                            <a href="#" class="btn btn-primary">Learn More</a>
                            <a href="#" class="btn btn-default">Go Back</a>
                        --></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <b>Times direct booking is &gt; OTA </b>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div style="width: 100%">
                            <canvas id="graph4" ></canvas>
                        </div>
                    </div>
                    <div class="panel-footer clearfix">
                        <div class="pull-right"><!--
                            <a href="#" class="btn btn-primary">Learn More</a>
                            <a href="#" class="btn btn-default">Go Back</a>
                        --></div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="../js/statistics.js"></script>
    </body>
</html>
