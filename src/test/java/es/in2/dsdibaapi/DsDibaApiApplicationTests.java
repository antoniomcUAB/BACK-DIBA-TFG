package es.in2.dsdibaapi;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.in2.dsdibaapi.controller.DiagnosticController;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.Municipi;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.FactorService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.MunicipiService;
import es.in2.dsdibaapi.service.PersonaService;
import es.in2.dsdibaapi.service.ProfessionalService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.RolService;
import es.in2.dsdibaapi.service.SituacioSocialService;
import es.in2.dsdibaapi.service.VersioModelService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DsDibaApiApplicationTests {
	
	@Autowired
	private DiagnosticController diagnosticController;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ExpedientService expedientService;
	
	@Autowired
	private EntornService entornService;
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private ContextualitzacioService contextualitzacioService;
	
	@Autowired
	private SituacioSocialService situacioSocialService;
	
	@Autowired
	private RiscService riscService;
	
	@Autowired
	private FactorService factorService;
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@Autowired
	private GravetatService gravetatService;
	
	@Autowired
	private VersioModelService versioModelService;
	
	@Autowired
	private ProfessionalService professionalService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private MunicipiService municipiService;
	
	
	Long gravetatBaixa=57801l;
	Long gravetatModerada=57802l;
	Long gravetaAlta =57803l;
	Long gravetaProteccio=57804l;
	Long gravetaRisc=57805l;
	
	Long riscSenseValoracio =57797l;
	Long riscVulnerabilitat =57798l;
	Long riscRisc=57799l;
	Long riscAltRisc=57800l;
	
	Long freqOcasional =  57792l;
	Long freqFrequent = 57793l;
	Long freqContinua = 57794l;
	Long freqPuntual = 57795l;
	Long freqSense = 57796l;
	
	Long ssA1 = 57813l;
	Long ssA2 = 57823l;
	Long entornAutonomia = 57812l;
	
	Long rolTecnic = 57807l;
	
	VersioModel versio;
	
	Expedient exp;
	
	Entorn entorn;
	
	

	//@Test 
	public void contextLoads() {
		
		
		Persona persona1 = personaService.save(Persona.builder().NOM("Persona1").COGNOM1("APE1").COGNOM2("APE2").build());
		Persona persona2 = personaService.save(Persona.builder().NOM("Persona2").COGNOM1("APE1").COGNOM2("APE2").build());
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Municipi municipi = municipiService.findById(57808l);
		
		Professional professional = professionalService.save(
												Professional.builder().nom("Professional1")
																	.cognom1("APE1")
																	.cognom2("APE2")
																	.municipi(municipi)
																	.rol(rols)
																	.build()); 
		versio = versioModelService.findById(57791l);
		
		exp =expedientService.save(Expedient.builder().EXPEDIENT("11/AAAA/99")
				.DATA(new Date())
				.estat("BORRADOR")
				.versioModel(versio)
				.OBSERVACIONS("adads")
				.professional(professional)
				.totalFamilia(2l)
				.NOM("Test 1")
				.persona(new HashSet<Persona>() {{
				            add(persona1);
				            add(persona2);
				        }}).build());
		
		
		
		
		
		
	}
	
	//@Test
	public void testCache () {
		
		Risc g = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		g = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		g = riscService.findById(54873l);
		g = riscService.findById(54873l);
		g = riscService.findById(54873l);
		g = riscService.findById(54873l);
		g = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		g = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		g = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
	}

	@Test
	public void addDiagnostics() {
		
		versio = versioModelService.findById(57791l);
		exp =expedientService.findById(58149l);
		entorn = entornService.findById(57812l);
	
	/*	SituacioSocial situacioSocial = situacioSocialService.findById(ssA1);
		
		Frequencia frequencia =frequenciaService.findById(freqOcasional);
		
		Risc risc = riscService.findById(riscVulnerabilitat);
		
		Gravetat gravetat = gravetatService.findById(gravetatBaixa);
		
		
		Diagnostic d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
		
		
		assertTrue(d.getFactor().getDESCRIPCIO().equalsIgnoreCase("Vulnerabilitat"));
		
		gravetat = gravetatService.findById(gravetatModerada);
		
		d.setGravetat(gravetat);
		
		diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
		
		assertTrue(d.getFactor().getDESCRIPCIO().equalsIgnoreCase("Risc"));
		
		frequencia =frequenciaService.findById(freqContinua);
		
		d.setFrequencia(frequencia);
		
		diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
		
		assertTrue(d.getFactor().getDESCRIPCIO().equalsIgnoreCase("Alt Risc"));
		
		situacioSocial = situacioSocialService.findById(ssA2);
		d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
		
		gravetat = gravetatService.findById(gravetatBaixa);
		frequencia =frequenciaService.findById(freqOcasional);
		
		d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
		
		//expedientService.avaluar(exp,entorn.getAmbit());
		
		*/
		/*Factor factor = factorService.findById(58060l);
		
		contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(58061l);
		
		contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).mesUc(true).factor(factor).build());*/
		
		Expedient newexp=expedientService.avaluar(exp);
		
		String dd = "s";
	}	
	
}

