package conteroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	private RequestDispatcher rdp;
	private MemberService msv;
	private int isOK;
	private String destPage;
	private MemberVO mvo;
	private String u8 = "utf-8";

	public MemberController() {
		msv = new MemberServiceImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(u8);
		response.setCharacterEncoding(u8);
		response.setContentType(u8);

		String uri = request.getRequestURI();
		log.info(uri);
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info(path);

		switch (path) {
		case "login":
			try {
				mvo = new MemberVO(request.getParameter("id"), request.getParameter("password"));
				MemberVO rmvo = new MemberVO();
				rmvo = msv.select_login(mvo);
				if (rmvo != null) {
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", rmvo);
					destPage = "/brd/page";
				} else {
					isOK = msv.select_id(mvo);
					if (isOK > 0) {
						isOK = 1;
					} else {
						isOK = -1;
					}
					request.setAttribute("idCheck", isOK);
					destPage = "/main.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "join":
			try {
				String id = request.getParameter("id");
				String pw = request.getParameter("password");
				if (id == null || id == "" || pw == null || pw == "") {
					request.setAttribute("nullid", true);
					destPage = "/member/join.jsp";
				} else {
					mvo = new MemberVO(id, pw, request.getParameter("email"));
					isOK = msv.join(mvo);
					if (isOK < 0) {
						request.setAttribute("overlap", true);
						log.info("중복된 아이디");
						destPage = "/member/join.jsp";
					} else {
						log.info("회원가입" + (isOK > 0 ? "성공" : "실패"));
						destPage = "/main.jsp";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case "modify":
			mvo = new MemberVO(request.getParameter("id"),request.getParameter("password"),request.getParameter("email"));
			isOK=msv.edit(mvo);
			break;
		case "logout":
			try {
				HttpSession ses = request.getSession();
				mvo = (MemberVO) ses.getAttribute("ses");
				String id = mvo.getId();
				isOK = msv.lastLogin(id);
				log.info(">>> logout : " + (isOK > 0 ? "성공" : "실패"));
				ses.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			destPage = "/";
			break;
		case "list":
			List<MemberVO> list = new ArrayList<MemberVO>();
			list = msv.getlist();
			request.setAttribute("list",list);
			destPage="/member/list.jsp";
			break;
		}
		

		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
