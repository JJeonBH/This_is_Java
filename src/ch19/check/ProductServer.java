package ch19.check;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductServer {
	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private List<Product> products;
	private int sequence;

	public void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		threadPool = Executors.newFixedThreadPool(100);
		products = new Vector<Product>();

		System.out.println("[서버] 시작됨");

		while (true) {
			Socket socket = serverSocket.accept();
			SocketClient sc = new SocketClient(socket);
		}
	}

	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdownNow();
			System.out.println("[서버] 종료됨");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class SocketClient {
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;

		public SocketClient(Socket socket) {
			try {
				this.socket = socket;
				this.dis = new DataInputStream(socket.getInputStream());
				this.dos = new DataOutputStream(socket.getOutputStream());
				receive();
			} catch (IOException e) {
				close();
			}
		}

		public void receive() {
			threadPool.execute(() -> {
				try {
					while (true) {
						String receiveJson = dis.readUTF();

						JSONObject request = new JSONObject(receiveJson);
						int menu = request.getInt("menu");

						switch (menu) {
						case 0 -> list();
						case 1 -> create(request);
						case 2 -> update(request);
						case 3 -> delete(request);
						}
					}
				} catch (IOException e) {
					close();
				}
			});
		}

		public void list() throws IOException {
			JSONArray data = new JSONArray();
			for (Product p : products) {
				JSONObject product = new JSONObject();
				product.put("no", p.getNo());
				product.put("name", p.getName());
				product.put("price", p.getPrice());
				product.put("stock", p.getStock());
				data.put(product);
			}

			JSONObject response = new JSONObject();
			response.put("status", "success");
			response.put("data", data);
			dos.writeUTF(response.toString());
			dos.flush();
		}

		public void create(JSONObject request) throws IOException {
			JSONObject data = request.getJSONObject("data");
			Product product = new Product();
			product.setNo(++sequence);
			product.setName(data.getString("name"));
			product.setPrice(data.getInt("price"));
			product.setStock(data.getInt("stock"));
			products.add(product);

			JSONObject response = new JSONObject();
			response.put("status", "success");
			response.put("data", new JSONObject());
			dos.writeUTF(response.toString());
			dos.flush();
		}

		public void update(JSONObject request) throws IOException {
			JSONObject data = request.getJSONObject("data");
			int no = data.getInt("no");
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				if (product.getNo() == no) {
					product.setName(data.getString("name"));
					product.setPrice(data.getInt("price"));
					product.setStock(data.getInt("stock"));
				}
			}

			JSONObject response = new JSONObject();
			response.put("status", "success");
			response.put("data", new JSONObject());
			dos.writeUTF(response.toString());
			dos.flush();
		}

		public void delete(JSONObject request) throws IOException {
			JSONObject data = request.getJSONObject("data");
			int no = data.getInt("no");
			Iterator<Product> iterator = products.iterator();
			while (iterator.hasNext()) {
				Product product = iterator.next();
				if (product.getNo() == no) {
					iterator.remove();
				}
			}

			JSONObject response = new JSONObject();
			response.put("status", "success");
			response.put("data", new JSONObject());
			dos.writeUTF(response.toString());
			dos.flush();
		}

		public void close() {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ProductServer productServer = new ProductServer();
		try {
			productServer.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			productServer.stop();
		}
	}
}