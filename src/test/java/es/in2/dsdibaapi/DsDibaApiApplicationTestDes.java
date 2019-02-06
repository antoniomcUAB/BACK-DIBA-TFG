package es.in2.dsdibaapi;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import es.in2.dsdibaapi.model.Pregunta;
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
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.FactorEconomicService;
import es.in2.dsdibaapi.service.FactorService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.MunicipiService;
import es.in2.dsdibaapi.service.PersonaService;
import es.in2.dsdibaapi.service.PreguntaService;
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
	private PreguntaController preguntaController;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private ExpedientService expedientService;
	
	@Autowired
	private EntornService entornService;
	
	@Autowired
	private EconomiaService economiaService;
	
	@Autowired
	private PreguntaService preguntaService;
	
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
	private EstatService estatService;
	
	@Autowired
	private MunicipiService municipiService;
	
	@Autowired
	private TipusPersonaService tipusPersonaService;
	
	Long gravetatBaixa=17774l;
	Long gravetatModerada=17775l;
	Long gravetaAlta =17776l;
	Long gravetaProteccio=17777l;
	Long gravetaRisc=17778l;
	
	Long riscSenseValoracio =17770l;
	Long riscVulnerabilitat =17771l;
	Long riscRisc=17772l;
	Long riscAltRisc=17773l;
	
	Long freqOcasional =  17765l;
	Long freqFrequent = 17766l;
	Long freqContinua = 17767l;
	Long freqPuntual = 17768l;
	Long freqSense = 17769l;
	
	Long ssA1 = 17790l;
	Long ssA2 = 17800l;
	
	Long ssH1 = 17812l;
	Long ssH2 = 17819l;
	
	Long ssES1 = 17898l;
	
	Long entornAutonomia = 17789l;
	Long entornHabitage = 17811l;
	Long entornEconomic = 17857l;
	Long entornEscolar = 17881l;
	
	Long rolTecnic = 17784l;
	
	Long versioId=17764l;
	
	Long municipiId = 17785l;
	
	Long factor1 = 18037l;
	Long factor2 = 18038l;
	
	Long factor3 = 18048l;
	Long factor4 = 18049l;
	Long factor5 = 18050l;
	Long factor6 = 18051l;
	
	Long factorEconomic1 = 18071l;
	Long factorEconomic2 = 18072l;
	
	Long pareMare = 18098l;
	
	Diagnostic diag;
	
	Entorn entorn;
	
	
	

	//@Test 
	public void contextLoads() {
		
		
		TipusPersona pare = tipusPersonaService.save(TipusPersona.builder().descripcio("Pare").build());
		TipusPersona mare = tipusPersonaService.save(TipusPersona.builder().descripcio("Mare").build());
		
		VersioModel versio;
		
		
		
		Persona persona1 = personaService.save(Persona.builder()
				.tipusPersona(pare)
				.sexe("h")
				.referencia(true)
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
		
		Expedient expedient = expedientService.save(Expedient.builder()
				.codi("11AAAA999")
				.dataCreacio(new Date ())
				.estat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()))
				.professional(professional)
				.totalFamilia(2l)
				.nom("Test 1.1")
				.persona(new HashSet<Persona>() {{
		            add(persona1);
		            add(persona2);
		        }})
				.build());
		
		diag =diagnosticService.save(Diagnostic.builder().expedient(expedient)
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.observacions("Test 1").build());
		
		diag =diagnosticService.save(Diagnostic.builder().expedient(expedient)
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.observacions("Test 2").build());
		
		expedient = expedientService.save(Expedient.builder()
				.codi("22AAAA999")
				.dataCreacio(new Date ())
				.estat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()))
				.professional(professional)
				.totalFamilia(2l)
				.nom("Test 1.1")
				.persona(new HashSet<Persona>() {{
		            add(persona1);
		            add(persona2);
		        }})
				.build());
		
		
		diag =diagnosticService.save(Diagnostic.builder().expedient(expedient)
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.observacions("Test 1").build());
		
		
	}
	
	
	//@Test
	public void addPreguntas() {
		
		VersioModel versio = versioModelService.findById(versioId);
		diag =diagnosticService.findById(62638l);
		entorn = entornService.findById(entornAutonomia);
	
		Pregunta p;
		
		SituacioSocial situacioSocial;
	
		situacioSocial = situacioSocialService.findById(ssA1);
		
		Frequencia frequencia =frequenciaService.findById(freqOcasional);
		
		Risc risc = riscService.findById(riscVulnerabilitat);
		
		Gravetat gravetat = gravetatService.findById(gravetatBaixa);
		
		
		p = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		p=preguntaController.putPregunta(diag.getId(), p);
		
		
		assertTrue(p.getFactor().getDescripcio().equalsIgnoreCase("Vulnerabilitat"));
		
		gravetat = gravetatService.findById(gravetatModerada);
		
		p.setGravetat(gravetat);
		
		p=preguntaController.putPregunta(diag.getId(), p);
		
		assertTrue(p.getFactor().getDescripcio().equalsIgnoreCase("Risc"));
		
		frequencia =frequenciaService.findById(freqContinua);
		
		p.setFrequencia(frequencia);
		
		p=preguntaController.putPregunta(diag.getId(), p);
		
		assertTrue(p.getFactor().getDescripcio().equalsIgnoreCase("Alt Risc"));
		
		situacioSocial = situacioSocialService.findById(ssA2);
		p = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		p=preguntaController.putPregunta(diag.getId(), p);
		
		gravetat = gravetatService.findById(gravetatBaixa);
		frequencia =frequenciaService.findById(freqOcasional);
		
		
		
		p=preguntaController.putPregunta(diag.getId(), p);
		
		
		
		
		//expedientService.avaluar(exp,entorn.getAmbit());
		
		
		
		Factor factor = factorService.findById(factor1);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor2);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).mesUc(true).factor(factor).build());
	
		
		Set<FactorEconomic> factors = new HashSet<FactorEconomic> ();
		
		factors.add(factorEconomicService.findById(factorEconomic1));
		factors.add(factorEconomicService.findById(factorEconomic2));
		
		Pregunta dEco=preguntaController.putPreguntaEconomia(diag.getId(), factors);
		
		
		Diagnostic newexp=diagnosticService.avaluar(diag.getId());
		
		String dd = "s";
	}	
	
	
	//@Test
	public void test1Valoracio() {
		
		TipusPersona mare = tipusPersonaService.findById(pareMare);
		
		VersioModel versio;
		
		Persona persona1 = Persona.builder()
				.tipusPersona(mare)
				.sexe("h")
				.dataNaixement(new Date())
				.dataAlta(new Date ()).build();
		Persona persona2 = Persona.builder().tipusPersona(mare).dataAlta(new Date ()).sexe("d").dataNaixement(new Date()).build();
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Municipi municipi = municipiService.findById(municipiId);
		
		Professional professional = 
												Professional.builder().nom("Professional11")
																	.cognom1("APE1")
																	.cognom2("APE2")
																	.municipi(municipi)
																	.rol(rols)
																	.build(); 
		versio = versioModelService.findById(versioId);
		
		Expedient expedient = expedientService.save(Expedient.builder()
				.codi("33AAAA999")
				.dataCreacio(new Date ())
				.estat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()))
				.professional(professional)
				.totalFamilia(2l)
				.nom("Test 1.1")
				.persona(new HashSet<Persona>() {{
		            add(persona1);
		            add(persona2);
		        }})
				.build());
		
		diag =diagnosticService.save(Diagnostic.builder().expedient(expedient)
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.observacions("Test 1").build());
		
		
		
		/*VersioModel versio = versioModelService.findById(versioId);
		exp =expedientService.findById(63001l);*/
		entorn = entornService.findById(entornAutonomia);
	
		Pregunta d;
		
		SituacioSocial situacioSocial;
	
		situacioSocial = situacioSocialService.findById(ssA1);
		
		Frequencia frequencia =frequenciaService.findById(freqOcasional);
		
		Risc risc = riscService.findById(riscVulnerabilitat);
		
		Gravetat gravetat = gravetatService.findById(gravetatBaixa);
		
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		
		assertTrue(d.getFactor().getDescripcio().equalsIgnoreCase("Vulnerabilitat"));
		
		Pregunta ssA1= d;
		
		
		situacioSocial = situacioSocialService.findById(ssA2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
	
		
		
		
		Diagnostic newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5d);
		
		
		Factor factor = factorService.findById(factor1);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.1d);
		
		entorn = entornService.findById(entornHabitage);
		
		situacioSocial = situacioSocialService.findById(ssH1);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqOcasional);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.6d);
		
		frequencia =frequenciaService.findById(freqContinua);
		
		d.setFrequencia(frequencia);
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 4.6d);
		
		
		
		factor = factorService.findById(factor3);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor4);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor5);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).mesUc(true).factor(factor).build());
		
		factor = factorService.findById(factor6);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		
		
		 situacioSocial = situacioSocialService.findById(ssH2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		entorn = entornService.findById(entornEscolar);
		
		situacioSocial = situacioSocialService.findById(ssES1);
		gravetat = gravetatService.findById(gravetatBaixa);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5.2d);
		
		frequencia =frequenciaService.findById(freqSense);
		d.setFrequencia(frequencia);
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 4.1d);
		
		
		situacioSocial = situacioSocialService.findById(ssES1);
		gravetat = gravetatService.findById(gravetatBaixa);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5.2d);
		
		frequencia =frequenciaService.findById(freqContinua);
		
		ssA1.setFrequencia(frequencia);
		
		ssA1=preguntaController.putPregunta(diag.getId(), ssA1);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 8.1d);
		 
	}	
	
	@Test
	public void test2Valoracio() {
		
		TipusPersona mare = tipusPersonaService.findById(pareMare);
		
		VersioModel versio;
		
		Persona persona1 = Persona.builder()
				.tipusPersona(mare)
				.sexe("h")
				.dataNaixement(new Date())
				.dataAlta(new Date ()).build();
		Persona persona2 = Persona.builder().referencia(true).tipusPersona(mare).dataAlta(new Date ()).sexe("d").dataNaixement(new Date()).build();
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Municipi municipi = municipiService.findById(municipiId);
		
		
		
		
		
		versio = versioModelService.findById(versioId);
		
		
		
		diag =Diagnostic.builder()
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.observacions("Test 1").build();
		
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic> ();
		
		diagnostics.add(diag);
		
		
		Expedient expedient = Expedient.builder()
				.codi("33AAAA999")
				.dataCreacio(new Date ())
				.estat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()))		
				.totalFamilia(2l)
				.diagnostic(diagnostics)
				.nom("Test 1.1")
				.persona(new HashSet<Persona>() {{
		            add(persona1);
		            add(persona2);
		        }})
				.build();
		
		List<Expedient> expedients = new ArrayList<Expedient> ();
		
		expedients.add(expedient);
		Professional professional = professionalService.save(
				Professional.builder().nom("Professional11")
									.cognom1("APE1")
									.cognom2("APE2")
									.municipi(municipi)
									.rol(rols)
									.expedient(expedients)
									.build()); 
		
		
		
		entorn = entornService.findById(entornAutonomia);
	
		Pregunta d;
		
		SituacioSocial situacioSocial;
	
		situacioSocial = situacioSocialService.findById(ssA1);
		
		Frequencia frequencia =frequenciaService.findById(freqOcasional);
		
		Risc risc = riscService.findById(riscVulnerabilitat);
		
		Gravetat gravetat = gravetatService.findById(gravetatBaixa);
		
		
		d = Pregunta.builder().entorn(entorn).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		
		
		
		String sdafsdggs= "";
		d=preguntaController.putPregunta(diag.getId(), d);
		
		
		assertTrue(d.getFactor().getDescripcio().equalsIgnoreCase("Vulnerabilitat"));
		
		Pregunta ssA1= d;
		
		
		situacioSocial = situacioSocialService.findById(ssA2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
	
		
		
		
		Diagnostic newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5d);
		
		
		Factor factor = factorService.findById(factor1);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.1d);
		
		entorn = entornService.findById(entornHabitage);
		
		situacioSocial = situacioSocialService.findById(ssH1);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqOcasional);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.6d);
		
		frequencia =frequenciaService.findById(freqContinua);
		
		d.setFrequencia(frequencia);
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 4.6d);
		
		
		
		factor = factorService.findById(factor3);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor4);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor5);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).mesUc(true).factor(factor).build());
		
		factor = factorService.findById(factor6);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		
		
		 situacioSocial = situacioSocialService.findById(ssH2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		entorn = entornService.findById(entornEconomic);
		
		situacioSocial = situacioSocialService.findById(ssES1);
		gravetat = gravetatService.findById(gravetatModerada);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 4.6d);
	
		
		
		/*
		
		situacioSocial = situacioSocialService.findById(ssES1);
		gravetat = gravetatService.findById(gravetatBaixa);
		
		d = Pregunta.builder().entorn(entorn).diagnostic(diag).situacioSocial(situacioSocial).risc(risc).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5.2d);
		
		frequencia =frequenciaService.findById(freqContinua);
		
		ssA1.setFrequencia(frequencia);
		
		ssA1=preguntaController.putPregunta(diag.getId(), ssA1);
		
		newexp=diagnosticService.avaluar(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 8.1d);
		 */
	}	
	
	//@Test
	public void test3Valoracio() {
		diagnosticService.avaluar(63028l);
	}
	
	
}

