package com.Sp4beans.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Sp4beans.wechat.service.MainService;
import com.Sp4beans.wechat.util.SignUtil;

public class MainServlet extends HttpServlet {
	
	public void init() {
		
	}
	
	/**
	 * ȷ����������΢�ŷ�����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ΢�ż�����
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();

		// ͨ������signature���������У�飬��У��ɹ�����ԭ������echostr��
		// ��ʾ����ɹ����������ʧ��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * ����΢�ŷ�������������Ϣ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO ��Ϣ�Ľ��ܣ�������Ӧ
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ���ú���ҵ���������Ϣ��������Ϣ
		String respMessage = MainService.processRequest(request);

		// ��Ӧ��Ϣ
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
}
