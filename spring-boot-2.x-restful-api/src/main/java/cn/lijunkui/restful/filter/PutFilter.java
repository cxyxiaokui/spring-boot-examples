package cn.lijunkui.restful.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * 解决restFul put 参数无法接收的问题
 */
@Component
public class PutFilter extends HttpPutFormContentFilter {

}
