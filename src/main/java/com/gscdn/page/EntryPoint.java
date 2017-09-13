package com.gscdn.page;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CachePeekMode;

import com.gscdn.model.AccessLog;

@Path("/")
public class EntryPoint {

	@GET
	@Path("page1")
	@Produces(MediaType.TEXT_PLAIN)
	public String func_(@Context HttpServletRequest req, @Context SecurityContext context) {

		String at = "\t";

		AccessLog log = new AccessLog();
		log.setAccessLog(req);

		StringBuffer strBuf = new StringBuffer();
		strBuf.append(log.getIp());
		strBuf.append(at);
		strBuf.append(log.getBrowser());
		strBuf.append(at);
		strBuf.append(log.getLanguage());
		strBuf.append(at);
		strBuf.append(log.getTime());

		Ignite ignite = Ignition.ignite();
		IgniteCache<String, String> cache = ignite.getOrCreateCache("Test");
		int size = cache.localSize(CachePeekMode.ALL); 
		size++;
		
		cache.put(Integer.toString(size), strBuf.toString());

		// for (int i = 0; i < 10; i++)
		// cache.put(Integer.toString(i), Integer.toString(i));

		return strBuf.toString();

	}

	@GET
	@Path("page2")
	@Produces(MediaType.TEXT_PLAIN)
	public String func_2(@Context HttpServletRequest req, @Context SecurityContext context) {

		Ignite ignite = Ignition.ignite();
		IgniteCache<String, String> cache = ignite.getOrCreateCache("Test");

		StringBuffer strBuf = new StringBuffer();

//		Iterator<Entry<String, String>> iter = cache.iterator();	
//		while (iter.hasNext()) {
//			Cache.Entry<String, String> cEntry = iter.next();			
//			String car_key = cEntry.getKey();
//			strBuf.append("[key=" + car_key + ", val=" + cache.get(car_key) + "] \n");
//		}

		int size = cache.localSize(CachePeekMode.ALL);
		int maxCount = size-3;
		
		for (int i = size; i>0; i--) {
			if(i==maxCount)	break;
			strBuf.append("[key=" + i + ", val=" + cache.get(Integer.toString(i)) + "] \n");
		}
		return strBuf.toString();
	}

}
