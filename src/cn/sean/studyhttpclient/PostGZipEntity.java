package cn.sean.studyhttpclient;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PostGZipEntity {

	public static void main(String args[]) throws Exception {
		System.out.println("aaa");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost("http://httpbin.org/post");
			//File file = new File("post.txt");
			
//			InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), ContentType.DEFAULT_BINARY);
//			httpPost.setEntity(reqEntity);
			
//			StringEntity reqEntity = new StringEntity("Post Data");
//			httpPost.setEntity(reqEntity);
//			String postData = "I'm post data";
//			ByteArrayEntity reqEntity = new ByteArrayEntity(postData.getBytes(), ContentType.DEFAULT_TEXT);
//			httpPost.setEntity(reqEntity);
			
//			FileReader fr = new FileReader(file);
//			ByteArrayOutputStream bao = new ByteArrayOutputStream();
//			IOUtils.copy(fr, bao);
//			byte[] data = new byte[3];
//			data[0] = 0;
//			data[1] = 55;
//			data[2] = 0;
//			ByteArrayEntity reqEntity = new ByteArrayEntity(data);
//			httpPost.setEntity(reqEntity);
			
//			byte[] data = new byte[3];
//			data[0] = 0;
//			data[1] = 1;
//			data[2] = 2;
//			ByteArrayInputStream bai = new ByteArrayInputStream(data);
//			InputStreamEntity reqEntity = new InputStreamEntity(bai, ContentType.APPLICATION_OCTET_STREAM);
//			reqEntity.setChunked(false);
//			httpPost.setEntity(reqEntity);
			
//			File file = new File("post.txt");
//			FileInputStream fin = new FileInputStream(file);
//			InputStreamEntity reqEntity = new InputStreamEntity(new BufferedInputStream(fin));
//			httpPost.setEntity(reqEntity);
			
			
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bao);
			gzip.write(IOUtils.toByteArray(new FileReader("ds_publish.xml")));
			bao.close();
			gzip.close();
			
//			GZIPInputStream unzip = new GZIPInputStream(new ByteArrayInputStream(bao.toByteArray()));
//			StringWriter sw = new StringWriter();
//			IOUtils.copy(unzip, sw);
//			System.out.println(sw.toString());
//			sw.close();
			
			ByteArrayEntity reqEntity = new ByteArrayEntity(bao.toByteArray());
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			
			
			try {
				HttpEntity resEntity = response.getEntity();
//				StringWriter writer = new StringWriter();
//				IOUtils.copy( resEntity.getContent(), writer);
//				System.out.println(writer.toString());
				System.out.println("------------------------------------------");
				System.out.println(response.getStatusLine());
				System.out.println("------------------------------------------");
				System.out.println(EntityUtils.toString(response.getEntity()));
				
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}
	
}
