package es.in2.dsdibaapi;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.in2.dsdibaapi.controller.DiagnosticController;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.TipusPersona;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EconomiaService;
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
import es.in2.dsdibaapi.service.TipusPersonaService;
import es.in2.dsdibaapi.service.VersioModelService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DsDibaApiApplicationTestDes {
	
	@Autowired
	private DiagnosticController diagnosticController;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ExpedientService expedientService;
	
	@Autowired
	private EntornService entornService;
	
	@Autowired
	private EconomiaService economiaService;
	
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
	
	@Autowired
	private TipusPersonaService tipusPersonaService;
	
	
	Long gravetatBaixa=13356l;
	Long gravetatModerada=13357l;
	Long gravetaAlta =13358l;
	Long gravetaProteccio=13359l;
	Long gravetaRisc=13360l;
	
	Long riscSenseValoracio =13352l;
	Long riscVulnerabilitat =13353l;
	Long riscRisc=13354l;
	Long riscAltRisc=13355l;
	
	Long freqOcasional =  13347l;
	Long freqFrequent = 13348l;
	Long freqContinua = 13349l;
	Long freqPuntual = 13350l;
	Long freqSense = 13351l;
	
	Long ssA1 = 13368l;
	Long ssA2 = 13378l;
	Long entornAutonomia = 13367l;
	
	Long rolTecnic = 13362l;
	
	VersioModel versio;
	
	Expedient exp;
	
	Entorn entorn;
	
	

	@Test 
	public void contextLoads() {
		
		
		TipusPersona pare = tipusPersonaService.save(TipusPersona.builder().descripcio("Pare").build());
		TipusPersona mare = tipusPersonaService.save(TipusPersona.builder().descripcio("Mare").build());
		
		
		Persona persona1 = personaService.save(Persona.builder().tipusPersona(pare).sexe("h").dataNaixement(new Date()).build());
		Persona persona2 = personaService.save(Persona.builder().tipusPersona(mare).sexe("d").dataNaixement(new Date()).build());
	/*	
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Municipi municipi = municipiService.findById(13363l);
		
		Professional professional = professionalService.save(
												Professional.builder().nom("Professional11")
																	.cognom1("APE1")
																	.cognom2("APE2")
																	.municipi(municipi)
																	.rol(rols)
																	.build()); 
		versio = versioModelService.findById(13346l);
		
		exp =expedientService.save(Expedient.builder().expedient("11AAAA999")
				.DATA(new Date())
				.estat("BORRADOR")
				.versioModel(versio)
				.OBSERVACIONS("adads")
				.professional(professional)
				.totalFamilia(2l)
				.NOM("Test 1.1")
				.persona(new HashSet<Persona>() {{
				            add(persona1);
				            add(persona2);
				        }}).build());
		
		exp =expedientService.save(Expedient.builder().expedient("11AAAA999")
				.DATA(new Date())
				.estat("BORRADOR")
				.versioModel(versio)
				.OBSERVACIONS("adads")
				.professional(professional)
				.totalFamilia(2l)
				.NOM("Test 1.2")
				.persona(new HashSet<Persona>() {{
				            add(persona1);
				            add(persona2);
				        }}).build());
		
		
		exp =expedientService.save(Expedient.builder().expedient("22AAAA999")
				.DATA(new Date())
				.estat("BORRADOR")
				.versioModel(versio)
				.OBSERVACIONS("adads")
				.professional(professional)
				.totalFamilia(2l)
				.NOM("Test 2")
				.persona(new HashSet<Persona>() {{
				            add(persona1);
				            add(persona2);
				        }}).build());
		
		*/
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

	//@Test
	public void addDiagnostics() {
		
		versio = versioModelService.findById(13346l);
		exp =expedientService.findById(13696l);
		entorn = entornService.findById(13367l);
	/*
		SituacioSocial situacioSocial = situacioSocialService.findById(ssA1);
		
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
		
		
		Factor factor = factorService.findById(13615l);
		
		contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(13616l);
		
		contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).mesUc(true).factor(factor).build());
		*/
		Expedient newexp=expedientService.avaluar(exp.getID());
		
		String dd = "s";
	}	
	
}

