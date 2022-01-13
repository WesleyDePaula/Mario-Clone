package redix;

//imports comuns
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

//imports est�ticos
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.Callbacks.*;

public class Window {

	private int width, height;
	private String title;

	private long glfwWindow; // Mem�ria alocada para a cria��o da "Janela"

	private static Window window = null;

	private Window() {
		this.width = 1920;
		this.height = 1080;
		this.title = "Mario Game Clone";
	}

	public static Window get() {

		if (Window.window == null) {
			Window.window = new Window();
		}

		return Window.window;
	}

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		// Liberando a mem�ria
		glfwFreeCallbacks(glfwWindow);
		glfwDestroyWindow(glfwWindow);
		
		// Terminando o GLFW e liberando o error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		
	}

	public void init() {

		// Setup an error callback
		GLFWErrorCallback.createPrint(System.err).set();

		// Inicializando o GLFW
		if (!glfwInit()) {
			throw new IllegalStateException("N�o foi poss�vel iniciar o GLFW.");
		}

		// Configurando o GLFW
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

		// Criando a "Janela"
		glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

		if (glfwWindow == NULL) {
			throw new IllegalStateException("Falha ao carregar a Janela do GLFW.");
		}

		// Make the OpenGl contxt current (n�o achei a tradu��o certa para isso, sorry
		// ;-;).
		glfwMakeContextCurrent(glfwWindow);

		// Habilitando o v-sync (recurso que sincroniza a taxa de frames com a taxa de
		// atualiza��o do monitor).
		glfwSwapInterval(1);

		// Fazendo a Janela ser vis�vel.
		glfwShowWindow(glfwWindow);

		// Isso � muito importante para o LWJGL operar juntamente com o contexto (?) do
		// GLFW OpenGL ou em qualquer contexto externo.
		// LWJGL detecta o contexto atual no thread, cria a instancia do GLCapabilities
		// e faz com que a "vincula��o" (Binding) esteja dispon�vel para uso.
		GL.createCapabilities();

	}

	public void loop() {

		while (!glfwWindowShouldClose(glfwWindow)) {

			// Poll Events
			glfwPollEvents();

			//"Seta" a cor da janela
			glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			
			glfwSwapBuffers(glfwWindow);
			
			
			
		}

	}

}
