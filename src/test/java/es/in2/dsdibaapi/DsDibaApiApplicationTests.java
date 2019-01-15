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
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.ContextualitzacioRepository;
import es.in2.dsdibaapi.repository.DiagnosticRepository;
import es.in2.dsdibaapi.repository.EntornRepository;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.repository.FrequenciaRepository;
import es.in2.dsdibaapi.repository.GravetatRepository;
import es.in2.dsdibaapi.repository.MunicipiRepository;
import es.in2.dsdibaapi.repository.PersonaRepository;
import es.in2.dsdibaapi.repository.ProfessionalRepository;
import es.in2.dsdibaapi.repository.RiscRepository;
import es.in2.dsdibaapi.repository.RolRepository;
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
	private ProfessionalRepository professionalRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private MunicipiRepository municipiRepository;
	
	@Autowired
	private Environment env;
	
	Long gravetatBaixa=54519l;
	Long gravetatModerada=54520l;
	Long gravetaAlta =54521l;
	Long gravetaProteccio=54522l;
	Long gravetaRisc=54523l;
	
	Long riscVulnerabilitat =54516l;
	Long riscRisc=54517l;
	Long riscAltRisc=54518l;
	
	Long freqOcasional =  54511l;
	Long freqFrequent = 54512l;
	Long freqContinua = 54513l;
	Long freqPuntual = 54514l;
	Long freqSense = 54515l;
	
	VersioModel versio;
	
	Expedient exp;
	
	Entorn entorn;
	

	@Test
	public void contextLoads() {
		
		/*
		
		Persona persona1 = personaRepository.save(new Persona ("Persona1","APE1","APE2"));
		Persona persona2 = personaRepository.save(new Persona ("Persona2","APE1","APE2"));
		
		Set<Rol> rols = new HashSet<Rol> ();
		
		
		rols.add(rolRepository.findById(54525l).get());
		
		Municipi municipi = municipiRepository.findById(54526l).get();
		
		Professional professional = professionalRepository.save(new Professional ("Professional1","APE1","APE2",municipi,rols));
		*/
		/*
		Professional professional = professionalRepository.findById(54858l).get();
		Persona persona1 = personaRepository.findById(54856l).get();
		Persona persona2 = personaRepository.findById(54857l).get();
				
		versio = versioModelRepository.findById(54510l).get();
		
		Expedient exp =expedientRepository.save(new Expedient ("11/AAAA/99", professional, "Test1", new Date (),"ddfd",new HashSet<Persona>() {{
            add(persona1);
            add(persona2);
        }},2l,versio,"BORRADOR"));*/
		
		exp =expedientRepository.findById(54861L).get();
		
		//Entorn entorn = entornRepository.findById(48554l);
		
		SituacioSocial situacioSocial = situacioSocialRepository.findById(54531l).get();
		
		Frequencia frequencia =frequenciaRepository.findById(freqOcasional).get();
		
		Risc risc = riscRepository.findById(riscVulnerabilitat).get();
		
		Gravetat gravetat = gravetatRepository.findById(gravetatBaixa).get();
		
		
		
		Diagnostic d = new Diagnostic (exp,entorn,situacioSocial,risc,frequencia,gravetat,true);		
	
		
		diagnosticController.putDiagnostic(exp.getID(), 54530l, d);
		
		
		assertTrue(d.getFactor().equalsIgnoreCase("Vulnerabilitat"));
		
		gravetat = gravetatRepository.findById(gravetatModerada).get();
		
		d.setGravetat(gravetat);
		
		diagnosticController.putDiagnostic(exp.getID(), 54530l, d);
		
		assertTrue(d.getFactor().equalsIgnoreCase("Risc"));
		
		frequencia =frequenciaRepository.findById(freqContinua).get();
		
		d.setFrequencia(frequencia);
		
		diagnosticController.putDiagnostic(exp.getID(), 54530l, d);
		
		assertTrue(d.getFactor().equalsIgnoreCase("Alt Risc"));
		
		
		
	}

	
	
}
