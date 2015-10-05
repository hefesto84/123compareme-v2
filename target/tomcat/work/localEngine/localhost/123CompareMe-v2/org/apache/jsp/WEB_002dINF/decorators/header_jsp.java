package org.apache.jsp.WEB_002dINF.decorators;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fdecorator_005fbody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fdecorator_005fbody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fdecorator_005fbody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<script src=\"../js/jquery-2.1.4.min.js\"></script>\n");
      out.write("<link rel=\"stylesheet\" href=\"../css/bootstrap.min.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"../css/bootstrap-theme.min.css\">\n");
      out.write("<script src=\"../js/bootstrap.min.js\"></script>\n");
      out.write("<!-- \n");
      out.write("<script src=\"https://code.jquery.com/jquery-2.1.4.min.js\"></script>\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css\">\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n");
      out.write("-->\n");
      out.write("<title>123Compare.me v.2</title>\n");
      out.write("</head>\n");
      out.write("<body  style=\"margin:1em;\">\n");
      out.write("\n");
      out.write("<nav class=\"navbar navbar-inverse\" role=\"navigation\">\n");
      out.write("  <div class=\"navbar-header\">\n");
      out.write("    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\n");
      out.write("            data-target=\".navbar-ex1-collapse\">\n");
      out.write("      <span class=\"sr-only\">Desplegar navegación</span>\n");
      out.write("      <span class=\"icon-bar\"></span>\n");
      out.write("      <span class=\"icon-bar\"></span>\n");
      out.write("      <span class=\"icon-bar\"></span>\n");
      out.write("    </button>\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">123 Compare.Me v.2</a>\n");
      out.write("  </div>\n");
      out.write(" \n");
      out.write("  <!-- Agrupar los enlaces de navegación, los formularios y cualquier\n");
      out.write("       otro elemento que se pueda ocultar al minimizar la barra -->\n");
      out.write("  <div class=\"collapse navbar-collapse navbar-ex1-collapse\">\n");
      out.write("    <ul class=\"nav navbar-nav\">\n");
      out.write("      <li class=\"active\"><a href=\"#\">Configuration</a></li>\n");
      out.write("      <li><a href=\"widget_configuration\">Widget</a></li>\n");
      out.write("      <li class=\"dropdown\">\n");
      out.write("        <a href=\"tools\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\n");
      out.write("          Tools <b class=\"caret\"></b>\n");
      out.write("        </a>\n");
      out.write("        <ul class=\"dropdown-menu\">\n");
      out.write("          <li><a href=\"general_reporting\">General Reporting</a></li>\n");
      out.write("          <li><a href=\"download_statistics\">Download Statistics</a></li>\n");
      out.write("          <li><a href=\"fast_price_check\">Fast Price Check</a></li>\n");
      out.write("        </ul>\n");
      out.write("      </li>\n");
      out.write("    </ul>\n");
      out.write("\n");
      out.write("    <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("      <li><a href=\"about\">About</a></li>\n");
      out.write("      <li class=\"dropdown\">\n");
      out.write("        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\n");
      out.write("          Help<b class=\"caret\"></b>\n");
      out.write("        </a>\n");
      out.write("        <ul class=\"dropdown-menu\">\n");
      out.write("          <li><a href=\"ht_manage_otas\">How to manage new OTAs</a></li>\n");
      out.write("          <li><a href=\"ht_check_dbstatus\">How to check DB status</a></li>\n");
      out.write("          <li class=\"divider\"></li>\n");
      out.write("          <li><a href=\"contact\">Contact with us</a></li>\n");
      out.write("        </ul>\n");
      out.write("      </li>\n");
      out.write("    </ul>\n");
      out.write("  </div>\n");
      out.write("</nav>\n");
      out.write("\n");
      if (_jspx_meth_decorator_005fbody_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_decorator_005fbody_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  decorator:body
    com.opensymphony.module.sitemesh.taglib.decorator.BodyTag _jspx_th_decorator_005fbody_005f0 = (com.opensymphony.module.sitemesh.taglib.decorator.BodyTag) _005fjspx_005ftagPool_005fdecorator_005fbody.get(com.opensymphony.module.sitemesh.taglib.decorator.BodyTag.class);
    _jspx_th_decorator_005fbody_005f0.setPageContext(_jspx_page_context);
    _jspx_th_decorator_005fbody_005f0.setParent(null);
    int _jspx_eval_decorator_005fbody_005f0 = _jspx_th_decorator_005fbody_005f0.doStartTag();
    if (_jspx_eval_decorator_005fbody_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_decorator_005fbody_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_decorator_005fbody_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_decorator_005fbody_005f0.doInitBody();
      }
      do {
        out.write('\n');
        int evalDoAfterBody = _jspx_th_decorator_005fbody_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_decorator_005fbody_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_decorator_005fbody_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fdecorator_005fbody.reuse(_jspx_th_decorator_005fbody_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fdecorator_005fbody.reuse(_jspx_th_decorator_005fbody_005f0);
    return false;
  }
}
