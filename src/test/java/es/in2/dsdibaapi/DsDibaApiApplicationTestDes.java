package es.in2.dsdibaapi;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.in2.dsdibaapi.controller.PreguntaController;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.Gravetat;
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
public class DsDibaApiApplicationTestDes {
	
	@Autowired
	private PreguntaController diagnosticController;
	
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
	
	Long gravetatBaixa=15193l;
	Long gravetatModerada=15194l;
	Long gravetaAlta =15195l;
	Long gravetaProteccio=15196l;
	Long gravetaRisc=15197l;
	
	Long riscSenseValoracio =15189l;
	Long riscVulnerabilitat =15190l;
	Long riscRisc=15191l;
	Long riscAltRisc=15192l;
	
	Long freqOcasional =  15184l;
	Long freqFrequent = 15185l;
	Long freqContinua = 15186l;
	Long freqPuntual = 15187l;
	Long freqSense = 15188l;
	
	Long ssA1 = 15205l;
	Long ssA2 = 15215l;	
	Long ssH1 = 62707l;
	
	Long entornAutonomia = 15204l;
	Long entornHabitage = 15226l;
	
	Long rolTecnic = 15199l;
	
	Long versioId=15183l;
	
	Long municipiId = 15200l;
	
	Long factor1 = 15452l;
	Long factor2 = 15453l;
	
	Long factorEconomic1 = 15486l;
	Long factorEconomic2 = 15487l;
	
	Long pareId = 15513l;
	
	Expedient exp;
	
	Entorn entorn;
	
	
	
	//@Test 
		public void contextLoads() {
			
			
			TipusPersona pare = tipusPersonaService.findById(pareId);
			
			VersioModel versio;
			
			Persona persona1 = personaService.save(Persona.builder()
					.tipusPersona(pare)
					.sexe("h")
					.dataNaixement(new Date())
					.dataAlta(new Date ()).build());
			Persona persona2 = personaService.save(Persona.builder().tipusPersona(pare).dataAlta(new Date ()).sexe("d").dataNaixement(new Date()).build());
			
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
			
			
		}
		


		//@Test
		public void addDiagnostics() {
			
			VersioModel versio = versioModelService.findById(versioId);
			exp =expedientService.findById(62638l);
			entorn = entornService.findById(entornAutonomia);
		/*	
			Diagnostic d;
			
			SituacioSocial situacioSocial;
		
			situacioSocial = situacioSocialService.findById(ssA1);
			
			Frequencia frequencia =frequenciaService.findById(freqOcasional);
			
			Risc risc = riscService.findById(riscVulnerabilitat);
			
			Gravetat gravetat = gravetatService.findById(gravetatBaixa);
			
			
			d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
			
			d=diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
			
			
			assertTrue(d.getFactor().getDESCRIPCIO().equalsIgnoreCase("Vulnerabilitat"));
			
			gravetat = gravetatService.findById(gravetatModerada);
			
			d.setGravetat(gravetat);
			
			d=diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
			
			assertTrue(d.getFactor().getDESCRIPCIO().equalsIgnoreCase("Risc"));
			
			frequencia =frequenciaService.findById(freqContinua);
			
			d.setFrequencia(frequencia);
			
			d=diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
			
			assertTrue(d.getFactor().getDESCRIPCIO().equalsIgnoreCase("Alt Risc"));
			
			situacioSocial = situacioSocialService.findById(ssA2);
			d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
			
			d=diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
			
			gravetat = gravetatService.findById(gravetatBaixa);
			frequencia =frequenciaService.findById(freqOcasional);
			
			
			
			diagnosticController.putDiagnostic(exp.getID(), entornAutonomia, d);
			
			
			
			
			//expedientService.avaluar(exp,entorn.getAmbit());
			
			
			
			Factor factor = factorService.findById(factor1);
			
			contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).membreUnic(true).factor(factor).build());
			
			factor = factorService.findById(factor2);
			
			contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).mesUc(true).factor(factor).build());
		
			
			Set<FactorEconomic> factors = new HashSet<FactorEconomic> ();
			
			factors.add(factorEconomicService.findById(factorEconomic1));
			factors.add(factorEconomicService.findById(factorEconomic2));
			
			Diagnostic dEco=diagnosticController.putDiagnosticEconomia(exp.getID(), entorn.getID(), factors);
			
			*/
			Expedient newexp=expedientService.avaluar(exp.getID());
			
			String dd = "s";
		}	
		
		
		@Test
		public void test1Valoracio() {
			
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
			
			exp =expedientService.save(Expedient.builder().expedient(UUID.randomUUID().toString())
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
			
			/*VersioModel versio = versioModelService.findById(versioId);
			exp =expedientService.findById(63001l);*/
			entorn = entornService.findById(entornAutonomia);
		
			Diagnostic d;
			
			SituacioSocial situacioSocial;
		
			situacioSocial = situacioSocialService.findById(ssA1);
			
			Frequencia frequencia =frequenciaService.findById(freqOcasional);
			
			Risc risc = riscService.findById(riscVulnerabilitat);
			
			Gravetat gravetat = gravetatService.findById(gravetatBaixa);
			
			
			d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
			
			d=diagnosticController.putDiagnostic(exp.getID(), d);
			
			
			assertTrue(d.getFactor().getDESCRIPCIO().equalsIgnoreCase("Vulnerabilitat"));
			
		
			
			
			situacioSocial = situacioSocialService.findById(ssA2);
			gravetat = gravetatService.findById(gravetatModerada);
			frequencia =frequenciaService.findById(freqContinua);
			
			d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
			
			d=diagnosticController.putDiagnostic(exp.getID(), d);
			
		
			
			
			
			Expedient newexp=expedientService.avaluar(exp.getID());
			
			assertTrue (newexp.getValoracio().getTotal() == 5d);
			
			
			Factor factor = factorService.findById(factor1);
			
			contextualitzacioService.save(Contextualitzacio.builder().expedient(exp).membreUnic(true).factor(factor).build());
		
			newexp=expedientService.avaluar(exp.getID());
			
			assertTrue (newexp.getValoracio().getTotal() == 2.1d);
			
			entorn = entornService.findById(entornHabitage);
			
			situacioSocial = situacioSocialService.findById(ssH1);
			gravetat = gravetatService.findById(gravetatModerada);
			frequencia =frequenciaService.findById(freqOcasional);
			
			d = Diagnostic.builder().entorn(entorn).expedient(exp).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
			
			d=diagnosticController.putDiagnostic(exp.getID(), d);
			
			newexp=expedientService.avaluar(exp.getID());
			
			assertTrue (newexp.getValoracio().getTotal() == 2.6d);
			
			frequencia =frequenciaService.findById(freqContinua);
			
			d.setFrequencia(frequencia);
			
			d=diagnosticController.putDiagnostic(exp.getID(), d);
			
			newexp=expedientService.avaluar(exp.getID());
			
			assertTrue (newexp.getValoracio().getTotal() == 4.6d);
			
		}	
		
		//@Test
		public void test2Valoracio() {
			expedientService.avaluar(63028l);
		}
		
		
	}
