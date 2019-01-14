package es.in2.dsdibaapi;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import es.in2.dsdibaapi.controller.DiagnosticController;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.ContextualitzacioRepository;
import es.in2.dsdibaapi.repository.DiagnosticRepository;
import es.in2.dsdibaapi.repository.EntornRepository;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.repository.FrequenciaRepository;
import es.in2.dsdibaapi.repository.GravetatRepository;
import es.in2.dsdibaapi.repository.PersonaRepository;
import es.in2.dsdibaapi.repository.RiscRepository;
import es.in2.dsdibaapi.repository.SituacioSocialRepository;
import es.in2.dsdibaapi.repository.VersioModelRepository;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DsDibaApiApplicationTests {
	
	@Autowired
	private DiagnosticController diagnosticController;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private ExpedientRepository expedientRepository;
	
	@Autowired
	private EntornRepository entornRepository;
	
	@Autowired
	private DiagnosticRepository diagnosticRepository;
	
	@Autowired
	private ContextualitzacioRepository contextualitzacioRepository;
	
	@Autowired
	private SituacioSocialRepository situacioSocialRepository;
	
	@Autowired
	private RiscRepository riscRepository;
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	@Autowired
	private GravetatRepository gravetatRepository;
	
	@Autowired
	private VersioModelRepository versioModelRepository;
	
	@Autowired
	private Environment env;
	
	Long gravetatBaixa=48548l;
	Long gravetatModerada=48549l;
	Long gravetaAlta =48550l;
	Long gravetaProteccio=48551l;
	Long gravetaRisc=48552l;
	
	Long riscVulnerabilitat =48545l;
	Long riscRisc=48546l;
	Long riscAltRisc=48547l;
	
	Long freqOcasional =  48540l;
	Long freqFrequent = 48541l;
	Long freqContinua = 48542l;
	Long freqPuntual = 48543l;
	Long freqSense = 48544l;
	
	VersioModel versio;
	
	Expedient exp;
	
	Entorn entorn;
	

	@Test
	public void contextLoads() {
		
		
		
		Persona persona1 = personaRepository.save(new Persona ("Persona1","APE1","APE2"));
		Persona persona2 = personaRepository.save(new Persona ("Persona2","APE1","APE2"));
		
		versio = versioModelRepository.findOne(48539l);
		
		/*Expedient exp =expedientRepository.save(new Expedient ("11/AAAA/99", "Professional", "Test1", new Date (),"ddfd",new HashSet<Persona>() {{
            add(persona1);
            add(persona2);
        }},2l,versio));*/
		
		exp =expedientRepository.findOne(48882l);
		
		//Entorn entorn = entornRepository.findOne(48554l);
		
		SituacioSocial situacioSocial = situacioSocialRepository.findOne(48555l);
		
		Frequencia frequencia =frequenciaRepository.findOne(freqOcasional);
		
		Risc risc = riscRepository.findOne(riscVulnerabilitat);
		
		Gravetat gravetat = gravetatRepository.findOne(gravetatBaixa);
		
		
		
		Diagnostic d = new Diagnostic (exp,entorn,situacioSocial,risc,frequencia,gravetat,true);		
	
		
		diagnosticController.putDiagnostic(exp.getID(), 48554l, d);
		
		
		assertTrue(d.getFactor().equalsIgnoreCase("Vulnerabilitat"));
		
		gravetat = gravetatRepository.findOne(gravetatModerada);
		
		d.setGravetat(gravetat);
		
		diagnosticController.putDiagnostic(exp.getID(), 48554l, d);
		
		assertTrue(d.getFactor().equalsIgnoreCase("Risc"));
		
		frequencia =frequenciaRepository.findOne(freqContinua);
		
		d.setFrequencia(frequencia);
		
		diagnosticController.putDiagnostic(exp.getID(), 48554l, d);
		
		assertTrue(d.getFactor().equalsIgnoreCase("Alt Risc"));
		
		
		
	}

	
	
}
