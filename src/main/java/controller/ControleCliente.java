package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import model.Cliente;

/**
 * Servlet implementation class ControleCliente
 */
@WebServlet("/ControleCliente")
public class ControleCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleCliente() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensagem;
		Cliente cliente = new Cliente();
		ClienteDAO clienteDao = new ClienteDAO();
		RequestDispatcher despacho = request.getRequestDispatcher("cadastroCliente.jsp");
		
		if (request.getParameter("name") != null && !request.getParameter("name").isEmpty() &&
				request.getParameter("email") != null && !request.getParameter("email").isEmpty() &&
				request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
			cliente.setNome(request.getParameter("name"));
			cliente.setEmail(request.getParameter("email"));
			cliente.setMensagem(request.getParameter("message"));
			
			clienteDao.cadastrar(cliente);
			
			
			mensagem = "Mensagem enviada com sucesso!";
		} else
			mensagem = "Mensagem não foi enviada.";
		request.setAttribute("mensagem", mensagem);
		despacho.forward(request, response);
	}

}
