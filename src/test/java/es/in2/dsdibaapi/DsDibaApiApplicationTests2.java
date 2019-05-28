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

import es.in2.dsdibaapi.controller.DiagnosticController;
import es.in2.dsdibaapi.controller.PreguntaController;
import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.AmbitDiagnostic;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Factor;
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
import es.in2.dsdibaapi.service.AmbitService;
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
public class DsDibaApiApplicationTests2 {
	
	@Autowired
	private PreguntaController preguntaController;
	
	@Autowired
	private DiagnosticController diagnosticController;
	
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
	private AmbitService ambitService;
	
	@Autowired
	private EstatService estatService;
	
	@Autowired
	private MunicipiService municipiService;
	
	@Autowired
	private TipusPersonaService tipusPersonaService;
	
	Long gravetatBaixa=72059l;
	Long gravetatModerada=72060l;
	Long gravetaAlta =72061l;
	Long gravetaProteccio=72062l;
	Long gravetaRisc=72063l;
	
	Long riscSenseValoracio =72055l;
	Long riscVulnerabilitat =72056l;
	Long riscRisc=72057l;
	Long riscAltRisc=72058l;
	
	Long freqOcasional =  72050l;
	Long freqFrequent = 72051l;
	Long freqContinua = 72052l;
	Long freqPuntual = 72053l;
	Long freqSense = 72054l;
	
	Long ssA1 = 72390l;
	Long ssA2 = 72400l;
	
	Long ssH1 = 72412l;
	Long ssH2 = 72419l;
	
	Long ssES1 = 72498l;
	
	Long entornAutonomia = 72389l;
	Long entornHabitage = 72411l;
	Long entornEconomic = 72457l;
	Long entornEscolar = 72497l;
	
	Long rolTecnic = 119L;
		
	
	Long versioId=72387l; //v2
	
	
	Long barcelonaId = 120L;
	Long lleidaId = 122L;
	Long tarragonaId = 121L;
	
	Long factor1 = 72333l;
	Long factor2 = 72334l;
	
	Long factor3 = 72344l;
	Long factor4 = 72345l;
	Long factor5 = 72346l;
	Long factor6 = 72347l;
	
	Long factorEconomic1 = 72367l;
	Long factorEconomic2 = 72368l;
	
	Long pareMare = 72080l;
	
	Diagnostic diag;
	
	Entorn entorn;
	
	
	
	//@Test
	public void test2Valoracio() {
		
		TipusPersona mare = tipusPersonaService.findById(pareMare);
		
		Iterable<Ambit> ambits = ambitService.findAll(versioId);
		
		VersioModel versio;
		
		Persona persona1 = Persona.builder()
				.tipusPersona(mare)
				.sexe("h")
				.dataNaixement(new Date())
				.dataAlta(new Date ()).build();
		Persona persona2 = Persona.builder().referencia(true).tipusPersona(mare).dataAlta(new Date ()).sexe("d").dataNaixement(new Date()).build();
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Municipi municipi = municipiService.findById(barcelonaId);
		
		
		
		
		
		versio = versioModelService.findById(versioId);
		
		
		List<AmbitDiagnostic> ambitsDiagnostic = new ArrayList<AmbitDiagnostic> ();
		
		for (Ambit a:ambits) {				
			ambitsDiagnostic.add(AmbitDiagnostic.builder().ambit(a).build());
		}
		
		diag =Diagnostic.builder()
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.ambit(ambitsDiagnostic)
				.observacions("Test 1").build();
		
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic> ();
		
		diagnostics.add(diag);
		
		ambitsDiagnostic = new ArrayList<AmbitDiagnostic> ();
		
		for (Ambit a:ambits) {				
			ambitsDiagnostic.add(AmbitDiagnostic.builder().ambit(a).build());
		}
		
		diag =Diagnostic.builder()
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.ambit(ambitsDiagnostic)
				.observacions("Test 2").build();
		
		diagnostics.add(diag);
		
		ambitsDiagnostic = new ArrayList<AmbitDiagnostic> ();
		
		for (Ambit a:ambits) {				
			ambitsDiagnostic.add(AmbitDiagnostic.builder().ambit(a).build());
		}
		
		diag =Diagnostic.builder()
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.ambit(ambitsDiagnostic)
				.observacions("Test 3").build();
		
		diagnostics.add(diag);
		
		
		Expedient expedient = Expedient.builder()
				.codi("33AAAA999")
				.dataCreacio(new Date ())
				.estat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()))		
				.totalFamilia(2l)
				.diagnostic(diagnostics)
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
		
		
		
		d = Pregunta.builder().situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		
		
		
		String sdafsdggs= "";
		d=preguntaController.putPregunta(diag.getId(), d);
		
		
		assertTrue(d.getFactor().getDescripcio().equalsIgnoreCase("Vulnerabilitat"));
		
		Pregunta ssA1= d;
		
		
		situacioSocial = situacioSocialService.findById(ssA2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
	
		
		//diag = diagnosticService.findById(diag.getId());
		
		//Diagnostic newexp=diagnosticService.avaluar(diag);
		
		Diagnostic newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5d);
		
		
		Factor factor = factorService.findById(factor1);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.1d);
		
		entorn = entornService.findById(entornHabitage);
		
		situacioSocial = situacioSocialService.findById(ssH1);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqOcasional);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.6d);
		
		frequencia =frequenciaService.findById(freqContinua);
		
		d.setFrequencia(frequencia);
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 4.6d);
		
		
		
		factor = factorService.findById(factor3);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor4);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor5);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).mesUc(true).factor(factor).build());
		
		factor = factorService.findById(factor6);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		
		
		 situacioSocial = situacioSocialService.findById(ssH2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		entorn = entornService.findById(entornEconomic);
		
		situacioSocial = situacioSocialService.findById(ssES1);
		gravetat = gravetatService.findById(gravetatModerada);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5.2d);
	
		
		
	}	
	
	//@Test
	public void test3Valoracio() {
		//diagnosticService.avaluar(63028l);
	}
	
	/*
	* TODO: Ejecutar este metodo para crear el profesional en su version inicial....
	*  Recordar cambiar el id del rol en esta clase y añadir al que se encuentra en mi db tabla DIBA_ROL_ROL el id del tecnico.
	* - Algunos datos del profesional como el rol y la region estan harcodeado en estos servicios, es necesario repasar bien esto.
	* Cambiarlo en la costante rolTecnic...
	*
	* Cambiar tambien la constante municipio y poner el identificador encontrado en municipio barcelona en la db DIBA_MUN_MUNICIPI
	* Cambiar las tres variables de municipio con los de la base de datos.
	* */
	@Test
	public void newProfessional () {
		
Municipi municipi = municipiService.findById(barcelonaId);
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
	/*	Professional professional = professionalService.save(
				Professional.builder().nom("PROFESSIONAL")
									.cognom1("APE31")
									.cognom2("APE32")
									.nomComplet("PROFESSIONAL APE31 APE32")
									.municipi(municipi)
									.username("PROFESSIONAL")
									.password("PROFESSIONAL")
									.rol(rols)
									.build());*/
		
		
		Professional professional = professionalService.save(
				Professional.builder().nom("PROFESSIONAL2")
									.cognom1("APE31")
									.cognom2("APE32")
									.nomComplet("PROFESSIONAL2 APE31 APE32")
									.municipi(municipi)
									.username("PROFESSIONAL")
									.password("PROFESSIONAL")
									.rol(rols)
									.build());
		
		municipi = municipiService.findById(lleidaId);
		
		professional = professionalService.save(
				Professional.builder().nom("PROFESSIONAL3")
									.cognom1("APE31")
									.cognom2("APE32")
									.nomComplet("PROFESSIONAL3 APE31 APE32")
									.municipi(municipi)
									.username("PROFESSIONAL")
									.password("PROFESSIONAL")
									.rol(rols)
									.build());
		

		
		professional = professionalService.save(
				Professional.builder().nom("PROFESSIONAL4")
									.cognom1("APE31")
									.cognom2("APE32")
									.nomComplet("PROFESSIONAL4 APE31 APE32")
									.municipi(municipi)
									.username("PROFESSIONAL")
									.password("PROFESSIONAL")
									.rol(rols)
									.build());
		
		municipi = municipiService.findById(tarragonaId);
		
		professional = professionalService.save(
				Professional.builder().nom("PROFESSIONAL5")
									.cognom1("APE31")
									.cognom2("APE32")
									.nomComplet("PROFESSIONAL5 APE31 APE32")
									.municipi(municipi)
									.username("PROFESSIONAL")
									.password("PROFESSIONAL")
									.rol(rols)
									.build());
		

		
		professional = professionalService.save(
				Professional.builder().nom("PROFESSIONAL6")
									.cognom1("APE31")
									.cognom2("APE32")
									.nomComplet("PROFESSIONAL6 APE31 APE32")
									.municipi(municipi)
									.username("PROFESSIONAL")
									.password("PROFESSIONAL")
									.rol(rols)
									.build());
	}
	
}

