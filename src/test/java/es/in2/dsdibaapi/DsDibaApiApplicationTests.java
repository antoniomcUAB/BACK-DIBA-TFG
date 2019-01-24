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
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Municipi;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.model.TipusPersona;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EconomiaService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.FactorEconomicService;
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
	private FactorEconomicService factorEconomicService;
	
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
	
	Long gravetatBaixa=62230l;
	Long gravetatModerada=62231l;
	Long gravetaAlta =62232l;
	Long gravetaProteccio=62233l;
	Long gravetaRisc=62234l;
	
	Long riscSenseValoracio =62226l;
	Long riscVulnerabilitat =62227l;
	Long riscRisc=62228l;
	Long riscAltRisc=62229l;
	
	Long freqOcasional =  62221l;
	Long freqFrequent = 62222l;
	Long freqContinua = 62223l;
	Long freqPuntual = 62224l;
	Long freqSense = 62225l;
	
	Long ssA1 = 62242l;
	Long ssA2 = 62252l;	
	Long ssEco = 62316l;
	
	Long entornAutonomia = 62241l;
	Long entornEconomic = 62309l;
	
	Long rolTecnic = 62236l;
	
	Long versioId=62220l;
	
	Long municipiId = 62237l;
	
	Long factor1 = 62489l;
	Long factor2 = 62490l;
	
	Long factorEconomic1 = 62523l;
	Long factorEconomic2 = 62524l;
	
	Expedient exp;
	
	Entorn entorn;
	
	
	

	//@Test 
	public void contextLoads() {
		
		
		TipusPersona pare = tipusPersonaService.save(TipusPersona.builder().descripcio("Pare").build());
		TipusPersona mare = tipusPersonaService.save(TipusPersona.builder().descripcio("Mare").build());
		
		VersioModel versio;
		
		Persona persona1 = personaService.save(Persona.builder()
				.tipusPersona(pare)
				.sexe("h")
				.dataNaixement(new Date())
				.dataAlta(new Date ()).build());
		Persona persona2 = personaService.save(Persona.builder().tipusPersona(mare).dataAlta(new Date ()).sexe("d").dataNaixement(new Date()).build());
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Municipi municipi = municipiService.findById(municipiId);
		
		Professional professional = professionalService.save(
												Professional.builder().nom("Professional11")
																	.cognom1("APE1")
																	.cognom2("APE2")
																	.municipi(municipi)
																	.rol(rols)
																	.build()); 
		versio = versioModelService.findById(versioId);
		
		exp =expedientService.save(Expedient.builder().expedient("11AAAA999")
				.DATA(new Date())
				.estat("BORRADOR")
				.versioModel(versio)
				.diagnosticEconomic(ssEco)
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
				.diagnosticEconomic(ssEco)
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
				.diagnosticEconomic(ssEco)
				.professional(professional)
				.totalFamilia(2l)
				.NOM("Test 2")
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
		
		VersioModel versio = versioModelService.findById(versioId);
		exp =expedientService.findById(62548l);
		entorn = entornService.findById(entornAutonomia);
		
		Diagnostic d;
		
		SituacioSocial situacioSocial;
	
	/*	situacioSocial = situacioSocialService.findById(ssA1);
		
		Frequencia frequencia =frequenciaService.findById(freqOcasional);
		
		Risc risc = riscService.findById(riscVulnerabilitat);
		
		Gravetat gravetat = gravetatService.findById(gravetatBaixa);
		
		
		d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
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
		
		
		
		diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
		
		
		
		
		//expedientService.avaluar(exp,entorn.getAmbit());
		
		
		
		Factor factor = factorService.findById(factor1);
		
		contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor2);
		
		contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).mesUc(true).factor(factor).build());
	*/
		
	/*	Set<FactorEconomic> factors = new HashSet<FactorEconomic> ();
		
		factors.add(factorEconomicService.findById(factorEconomic1));
		factors.add(factorEconomicService.findById(factorEconomic2));
		
		Diagnostic dEco=diagnosticController.putDiagnosticEconomia(exp.getID(), entorn.getID(), factors);*/
		
		/*entorn = entornService.findById(entornEconomic);
		situacioSocial = situacioSocialService.findById(ssEco);
		d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).build();
		
		
		
		FactorEconomic f = factorEconomicService.findById(factorEconomic1);
		
		//Factor sense = factorService.findById(gra)
		
		d=diagnosticController.putDiagnostic(exp.getID(), entornEconomic, d);
		
		economiaService.save(Economia.builder().diagnostic(d).factor(f).build());
		f = factorEconomicService.findById(factorEconomic2);
		economiaService.save(Economia.builder().diagnostic(d).factor(f).build());
		
		d=diagnosticController.putDiagnostic(exp.getID(), entornEconomic, d);
		*/
		Expedient newexp=expedientService.avaluar(exp.getID());
		
		String dd = "s";
	}	
	
	
}

