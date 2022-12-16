package com.example.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Random;


@RestController
public class UrlController{
	
	
	Random rand=new Random();
	int count=0;
	
	@Autowired
	private UrlRepository repository;

	
	
		@RequestMapping("/hello")
		public String hello()
		{
			return "hello world";
			
		}
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("pu");
		EntityManager em=emf.createEntityManager();
		URL u=new URL();
		public void commit(String url,int count,short key)
		{
			u.setCount(count);
			u.setUrl(url);
			u.setUniquekey(key);
			em.getTransaction().begin();
			    em.persist(u);
			    em.getTransaction().commit();	
			    em.clear();
		}
		
		@PostMapping(value="/storeurl")
		public void storeUrl(@RequestBody String url)
		{
	      short key=(short) rand.nextInt(1000);
	      count=0;
	     commit(url,count,key);
	    }
		@PostMapping(value= "/get")
		public short getUrl(@RequestBody String url)
		{
	      short key=(short) rand.nextInt(1000);
	      count++;
	      commit(url,count,key);
	      return key;
	    }
		@PostMapping("/count")
		public int count(@RequestBody String url)
		{
			short key=(short) rand.nextInt(1000);
		    count++;
			 commit(url,count,key);
		    return count;
		}

		@GetMapping("/list")
		public Page<URL> listUrl(@RequestParam int page,@RequestParam int size)
		{
			Pageable paging=PageRequest.of(page, size);
			return repository.findAll(paging);
		}
		
		
}
