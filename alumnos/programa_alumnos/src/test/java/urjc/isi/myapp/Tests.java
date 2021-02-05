package urjc.isi.myapp;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;


import static org.mockito.Mockito.*;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import java.io.File;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Before;
import org.junit.Rule;



public class Tests {
	Git git; 
	File directory;
	File zip;
	@Before
	public void setup() {
		git = Main.createRepo();
		Main.setGit(git);
		Main.setName("Pepe");
		Main.setMail("Pepe@gmail.com");
		Main.setDni("02223478L");
		Main.setIdEx("4211");
	}
	
	@Test
	public void testCreateRepository()
			throws IOException, GitAPIException {
		
		
		
			assertNotNull(git.getRepository());
		
	}
	@Test
	public void testCreateDirectory()
			throws IOException, GitAPIException {
			directory = new File("../examen/.git/");
		
		
			assertEquals(directory.exists(), true);
		
	}
	@Test
	public void testCreateZip()
			throws IOException{
		zip =Main.compressRepo();
		File test = new File("../" + Main.getIdEx() + "_" + Main.getDni() + ".zip");
		
		assertEquals(zip.exists(), true);
		assertEquals(zip, test);
		
	}
	
	
	@Test
	public void testDoCommit() throws IOException, GitAPIException {
		String name = null;
		String email = null;
		String message = null;
		Main.doCommit("Soy el primer commit");
		Iterable <RevCommit> log = null;
		log=git.log().all().call();
		for (RevCommit rev : log){
			name = rev.getAuthorIdent().getName();
			email = rev.getAuthorIdent().getEmailAddress();
			message = rev.getFullMessage();
		}
		assertEquals(Main.getName(), name);
		assertEquals(Main.getMail(), email);
		assertEquals("Soy el primer commit", message);

		
	}
	
/*	@Test
	public void testFinEx() throws IOException, Exception{
		Main.finEx();
		
		
	}*/
	/*@Test
	public void testCommits() throws IOException, GitAPIException, InterruptedException {
		
		Main.setAlarm();
		
		Iterable <RevCommit> log = null;
		log=git.log().all().call();
		int counter = 0;
		for (RevCommit i : log) {
		    counter++;
		}
		assertEquals(counter, 1);
		
	}*/
	


}
