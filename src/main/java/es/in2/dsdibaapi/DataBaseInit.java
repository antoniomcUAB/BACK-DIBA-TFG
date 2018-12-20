package es.in2.dsdibaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Criteri;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.FactorGravetat;
import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.FrequenciaGravetat;
import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.repository.CriteriRepository;
import es.in2.dsdibaapi.repository.EntornRepository;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import es.in2.dsdibaapi.repository.FactorGravetatRepository;
import es.in2.dsdibaapi.repository.FactorRepository;
import es.in2.dsdibaapi.repository.FrequenciaGravetatRepository;
import es.in2.dsdibaapi.repository.FrequenciaRepository;
import es.in2.dsdibaapi.repository.GravetatRepository;
import es.in2.dsdibaapi.repository.PersonaRepository;
import es.in2.dsdibaapi.repository.RiscRepository;
import es.in2.dsdibaapi.repository.SituacioSocialRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(name = "api.db-init", havingValue = "true")
public class DataBaseInit implements CommandLineRunner {
	
	@Autowired
	private AmbitRepository ambitRepository; 
	
	@Autowired
	private RiscRepository riscRepository;
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	@Autowired
	private GravetatRepository gravetatRepository;
	
	@Autowired
	private EntornRepository entornRepository;
	
	@Autowired
	private SituacioSocialRepository situacioSocialRepository;
	
	@Autowired
	private FrequenciaGravetatRepository frequenciaGravetatRepository;
	
	@Autowired
	private CriteriRepository criteriRepository;
	
	@Autowired
	private FactorRepository factorRepository;
	
	@Autowired
	private FactorGravetatRepository factorGravetatRepository;
	
	@Autowired
	private FactorEconomicRepository factorEconomicRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private ExpedientRepository expedientRepository;
	
	@Value("${add.test}")
	private Boolean addTest;

	@Override
	public void run(String... args) throws Exception {
		
		
		
		Frequencia ocasionalFrequencia=frequenciaRepository.save(new Frequencia("Ocasional"));
		Frequencia frequentFrequencia=frequenciaRepository.save(new Frequencia("Freqüent"));
		Frequencia continuaFrequencia=frequenciaRepository.save(new Frequencia("Continua"));
		Frequencia puntualFrequencia=frequenciaRepository.save(new Frequencia("Puntual"));
		Frequencia senseFrequencia=frequenciaRepository.save(new Frequencia("Sense valoració"));		
		
		Risc vulnerabilitatRisc = riscRepository.save(new Risc("Vulnerabilitat"));
		Risc riscRisc = riscRepository.save(new Risc("Risc"));
		Risc alttRisc = riscRepository.save(new Risc("Alt Risc"));
		
		Gravetat baixaGravetat = gravetatRepository.save(new Gravetat("Baixa"));		
		Gravetat moderadaGravetat= gravetatRepository.save(new Gravetat("Moderada"));
		Gravetat  altaGravetat=gravetatRepository.save(new Gravetat("Alta"));
		Gravetat proteccioGravetat=gravetatRepository.save(new Gravetat("Protecció"));
		Gravetat riscGravetat=gravetatRepository.save(new Gravetat("Risc"));
		
		Ambit ambit = ambitRepository.save(new Ambit ("Autonomia"));
		
		Entorn entornAutonomia = entornRepository.save (new Entorn ("Autonomia",ambit));
		
		SituacioSocial situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca d’autonomia personal a les  activitats bàsiques de la vida diaria  (A.1)",
																						"Situació en què la persona no pot desenvolupar les activitats bàsiques de la vida diaria (alimentació, higiene, seguiment mèdic i farmacològic, mobilitat personal, descans, etc.), independentment de la causa que la motiva: malaltia física o psíquica, trastorn mental, deteriorament cognitiu, discapacitat física, psíquica,  intel·lectual o sensorial, etc.",entornAutonomia));
		
		FrequenciaGravetat frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió en algun moment puntual de la setmana/dia",baixaGravetat));
		
		criteriRepository.save(new Criteri ("Pèrdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Crònica, sense possiblitats de millora",continuaFrequencia,riscRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió per les activitats de la vida diària",moderadaGravetat)); 
		
		criteriRepository.save(new Criteri ("Pèrdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Crònica, sense possiblitats de millora",continuaFrequencia,alttRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(
									new FrequenciaGravetat(situacioSocial,
											"Necessita atenció/supervisió continua les 24h",
											moderadaGravetat)); 
		
		criteriRepository.save(new Criteri ("Pèrdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Crònica, sense possiblitats de millora",continuaFrequencia,alttRisc,frequenciaGravetat));
				
				
		situacioSocial = situacioSocialRepository.save(
							new SituacioSocial ("Manca d’autonomia personal a les  activitats bàsiques de la vida diaria  (A.1)",
												"Situació en què la persona no pot desenvolupar les activitats instrumentals de la vida diaria (utilització del transport públic, realització de tasques domèstiques, responsabilitat sobre la medicació, administració de diners, capacitat d’utilitzar el telèfon, entrar i sortir del domicili de forma autònoma, etc.), independentment de la causa que la motiva: malaltia física o psíquica, trastorn mental, deteriorament cognitiu, discapacitat física, psíquica,  intel·lectual o sensorial, etc.",
												entornAutonomia));
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita atenció/supervisió puntual en algun moment del dia/setmana",
						baixaGravetat)); 
		
		criteriRepository.save(new Criteri ("Pèrdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Crònica, sense possiblitats de millora",continuaFrequencia,riscRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió per les activitats de la vida diària",moderadaGravetat));
		

		criteriRepository.save(new Criteri ("Pèrdua sobtada per causes sobrevingudes i possiblitats de millora",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Crònica, sense possiblitats de millora",puntualFrequencia,alttRisc,frequenciaGravetat));
		
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita atenció/supervisió continua les 24h",altaGravetat));		
		
		criteriRepository.save(new Criteri ("Pèrdua sobtada per causes sobrevingudes i possiblitats de millora",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Crònica, sense possiblitats de millora",puntualFrequencia,alttRisc,frequenciaGravetat));						
		
		
		ambit = ambitRepository.save(new Ambit ("MATERIAL I INSTRUMENTAL"));				
				
		Entorn entornHabitatge = entornRepository.save (new Entorn ("Entorn habitatge",ambit));
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca d'habitatge (H.1)","Situacions de manca d'un habitatge estable on fixar la residència. Inclou persones en situacions de sense sostre o sense llar de les categories ETHOS i inclou persones acollides temporalment en una institució a través de xarxa o en domicilis de familiars o amics persones que perden l'acolliment al domicili on vivien o que fixen el seu domicili en habitacles que no es consideren un habitatge (per exemple un cotxe).",entornHabitatge));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenció/supervisió en algun moment puntual de la setmana/dia",moderadaGravetat));
		criteriRepository.save(new Criteri ("Sobrevinguda",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Més d'un cop a l'any",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Sense sostre",puntualFrequencia,alttRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense xarxa de suport ni possiblitat d'activar recurs d'habitage. Amb xarxa de suport o habitatge temporal d'urgència i presència de menors o dependents a càrrec",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Habitatge deficient (H.2)","Situacions que es corresponen a la categoria ETHOS habitatge inadequat i inclou domicili que no reuneix les condicions adequades d'habitabilitat o les necessàries per garantir l'autonòmia i qualitat de vida de les persones que hi habiten. Inclou infrahabitatge (caravana i barraquisme). Inclou les situacions relacionades amb manca d'higiene/neteja greus.",entornHabitatge));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense risc en  la integritat física de les persones que hi habiten.",moderadaGravetat));
		criteriRepository.save(new Criteri ("Situacions excepcionals i restaruables",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Situacions estructurals ",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Existeix risc en la integritat física de les persones que hi habiten ",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Habitatge insegur (H.3)","	",entornHabitatge));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació impagament prèvia a l’inici del procés judicial. Ocupació il·legal",baixaGravetat));
		criteriRepository.save(new Criteri ("Situació puntual i capcitat de resolució",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Inferior 3 mesos consecutius",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Més de 3 mesos. Endeutament creixent",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Procés judicial iniciat",moderadaGravetat));
		criteriRepository.save(new Criteri ("Capacitat mediació. Moratòria",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("En procés judicial sense capacitat mediació. 	Moratòria",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("En subhasta",puntualFrequencia,alttRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sentència amb ordre de llançament",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));
								
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Habitatge massificat (H.4)","Situació d'habitatge massificat que es troba a la categoria ETHOS d'habitatge inadequat. Relació inadequada entre el nombre de persones que conviuen en un domicili i la superfície de l'habitatge, que no permet satisfer les activitats de la vida diària de forma satisfactòria. / salubritat llei habitatge.",entornHabitatge));
								
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Un sol nucli familiar en espai reduït. Persones joves que comparteixen espais",baixaGravetat));
							
		criteriRepository.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Fa més de 3 mesos",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"«Invasió familiar»: existeix un nucli familiar i arriben de forma sobtada nous membres.",moderadaGravetat));
		criteriRepository.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Fa més de 3 mesos",puntualFrequencia,riscRisc,frequenciaGravetat));
								
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Diverses famílies amb menors o dependents compartint espais. Situacions de relloguers sense contracte.",altaGravetat));
		criteriRepository.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Fa més de 3 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));
								
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Risc de pèrdua o manca serveis/subministraments (H.5)","Risc de perdua o manca de subministraments (gas, aigua, electricitat), manca de parament bàsic o estris domèstics imprescindibles per satisfer les necessitats bàsiques de la unitat familiar. Inclou els risc de perdua per connexió il·legal.",entornHabitatge));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Avís de tall. Manca de subministrament i és possible el restabliment",baixaGravetat));
		criteriRepository.save(new Criteri ("Primer cop",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Segon cop durant l’any",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Més de 2 cops durant any",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Avís de tall i manca de més d'un subministrament però és possible el restabliment",moderadaGravetat));
		criteriRepository.save(new Criteri ("Primer cop",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Segon cop durant l’any",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Més de 2 cops durant any",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No es pot restablir el servei",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));

		
		Entorn entornEconomic = entornRepository.save (new Entorn ("Entorn Econòmic",ambit));

		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Sense ingressos (E.1)","Unitats familiars que no tenen ingressos o que no els acrediten de forma estable o regular. Es proposa Comptabilitzar en aquesta categoria aquelles unitats familiars on s’han identificat més de 6 ítems dels qüestionari.",entornEconomic));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Disposen de xarxa o recursos que proporcionen  suport econòmic. No han d’afrontar pagament de l’habitatge habitual",moderadaGravetat));
		criteriRepository.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua/crònica",puntualFrequencia,alttRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No disposen de xarxa o recursos de suport econòmic",moderadaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
							
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Ingressos insuficients (E.2)","Unitats familiars que malgrat tenir uns ingressos tenen dificultats per cobrir determinades necessitats  dels seus integrants.",entornEconomic));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"3-4 ítems del qüestionari privació econòmica i amb xarxa/recursos de suport econòmic",baixaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"3-4 ítems del qüestionari sense xarxa de suport o. 5-6 amb xarxa familiar de suport que proporciona els recursos",moderadaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));						
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"5 o més ítems del qüestionari sense xarxa/recursos de suport econòmic",altaGravetat));
									
		criteriRepository.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua i poca capacitat de canvi",puntualFrequencia,alttRisc,frequenciaGravetat));	
							
							
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Administració deficient (E.3)","Unitat familiar amb ingressos però que presenta dificultats per administrar el pressupost  i no prioritzar la cobertura de les necessitats bàsiques.",entornEconomic));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No saben fer front a despeses extres inesperades",baixaGravetat));
		criteriRepository.save(new Criteri ("Situacions concretes  i capacitat de canvi",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua",puntualFrequencia,riscRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Degut a la mala gestió tenen dificultats per cobrir les despeses bàsiques de la unitat familiar ",moderadaGravetat));
		criteriRepository.save(new Criteri ("Situacions concretes  i capacitat de canvi",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua",puntualFrequencia,alttRisc,frequenciaGravetat));						
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"La mala gestió és cròncia i amb greus afectacions a la vída diària",altaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
										
		Entorn entornLaboral = entornRepository.save (new Entorn ("Entorn Laboral",ambit));
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de qualificació i/o d'orientació laboral  (L.1)","La persona té dificultats per trobar una feina estable perquè desconeix els mecanismes per accedir al mercat laboral i no disposa de la formació i qualificació suficient per trobar una feina estable. En conseqüència, entra i surt del mercat laboral, realitza treballs que no donen continuïtat a la seva progressió professional o resta llargues temporades a l'atur.  Addicionalment, la persona pot tenir dificultats personals o legals que dificulten l'accés al mercat de treball o el manteniment d'una  feina.",entornLaboral));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Voluntat de treballar però dificultat en l’accés al mercat de treball. Situacions sobrevingudes (salut, situació legal en tràmit, etc..)",baixaGravetat));		
		criteriRepository.save(new Criteri ("Ha treballat en el darrer any",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Ha treballat 6 mesos dels darrers 3 anys",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Atur de llarga durada/ fa més de 3 anys que no treballa",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Les dificultats en el manteniment de les carregues familiars i la cura agreujen la situació ",altaGravetat));		
		criteriRepository.save(new Criteri ("Ha treballat en el darrer any",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Ha treballat 6 mesos dels darrers 3 anys",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Atur de llarga durada/ fa més de 3 anys que no treballa",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de competències professionals transversals  (L.2)","La persona desconeix o no té les competències professionals necessàries per accedir al mercat laboral i mantenir una feina. En conseqüència, entra i surt del mercat laboral per motius que tenen a veure amb l'actitud i la responsabilitat cap a la feina (inpuntualitat, manca de respecte als companys/es de feina o superior jeràrquic, imatge personal no adaptada als requeriments de la feina, manca d'anticipació de problemàtiques, etc).",entornLaboral));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Consciència de canvi i voluntat de millora",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));								
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Impossibilitat de canvi o millora a mig termini (1 any)",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));	
											
											
		ambit = ambitRepository.save(new Ambit ("RELACIONAL"));						
											
		Entorn entornEscolar = entornRepository.save (new Entorn ("Entorn Escolar",ambit));
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats d'aprenentatge escolar (ES.1)","Infant o adolescent que presenta dificultats en el seguiment educatiu que li correspon com procés evolutiu, com a conseqüència de dinàmiques familiars adverses (inclouen desvinculació del centre educatiu, manca de recolzament, etc.).",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Dificultats associades a les capacitats de l'infant, identificades i existeix una intervenció conjunta per part dels diferents serveis. Situacions puntuals que afecten a la seva escolaritat",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervenció conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Família que entorpeix la intervenció social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l’infant o adolescent",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de competències relacionals a l'escola (ES.2)","Infant o adolescent que no s'adapta al sistema educatiu en l'etapa obligatòria i que presenta conductes disruptives al centre, amb els adults i/o amb els seus iguals.",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," Dificultats identificades i existeix una intervenció conjunta per part dels diferents serveis",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervenció conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Família que entorpeix la intervenció social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l’infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Escolaritat obligatòria inacabada (ES.3)","Menor en edat d'escolarització obligatòria que no està matriculat en cap centre, o que ha abandonat els estudis abans de la seva finalització.",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació puntual que afecta a la tramitació de la seva escolaritat però amb voluntat i capacitat de resolució en un curt termini (mateix curs escolar)",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació amb marge de millora que requereix una intervenció conunta  dels diferents serveis i recursos especialitzats.",moderadaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," No hi ha voluntat de canvi ni millora, La família incentiva o no col·labora per resoldre la situació. Afectacions molt greus en el desenvolupament integral de l’infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Absentisme escolar (ES.4)","No assistència al centre en que esta matriculat, de forma reiterada i no justificada, de l’alumnat en edat escolar (dels 3 als 16 anys) ja sigui per pròpia voluntat o per voluntat de la família.",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació puntaul per causa sobrevenguida (Malaltia o motius de salut/ familiars)",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervenció conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situació.",moderadaGravetat));		
		criteriRepository.save(new Criteri ("De 2 a 5 vegades al mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Més de 5 vegades al mes",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("No assiteix més de 5 dies consecutius",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"La família incentiva o no col·labora per resoldre l'absentisme",altaGravetat));		
		criteriRepository.save(new Criteri ("De 2 a 5 vegades al mes",frequentFrequencia,alttRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Més de 5 vegades al mes",continuaFrequencia,alttRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("No assiteix més de 5 dies consecutius",puntualFrequencia,alttRisc,frequenciaGravetat));					
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Assetjament escolar (ES.5)","Infant o adolescent que està patint Situacions habituals de violència física i psíquica en el marc de l’escola, o que s’ha iniciat en aquest marc encara que les actuacions es produeixin fora, per part dels seus companys i companyes, ja sigui en l’entorn escolar o fora d’ell",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," S'identifiquen situacions de comentaris despectius provinents d'una sola persona.",baixaGravetat));					
		criteriRepository.save(new Criteri ("Fet puntual o en un període de màxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"S'identifiquen  situacions d'agressions verbals per part de grup reduït",moderadaGravetat));					
		criteriRepository.save(new Criteri ("Fet puntual o en un període de màxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Agressions físiques o agressions verbals provinents de gran grup",altaGravetat));					
		criteriRepository.save(new Criteri ("Fet puntual o en un període de màxim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma reiterada i en més d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));
											
		Entorn entornFamiliar = entornRepository.save (new Entorn ("Entorn Familiar",ambit));
											
				
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Maltractament (F.1)","Situacions de maltractament que rep un menor d'edat,  adult o  persona gran, inclou vexacions de caràcter físic, psicològic o emocional, així com les situacions de violència de gènere. Inclou la sospita per part del professional o bé l'evidència que el maltractament s'està produint.(Es consideren incloses en quest indicador totes les categories de maltractament relacionades al RUDEL)",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense perill físic i l’afectació a nivell psíquic/emocional no interfereix en les competències bàsiques de la persona (autocura, comunicació, autonomia i iniciativa)",moderadaGravetat));					
		criteriRepository.save(new Criteri ("1 cop a l'any/ vida",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Més d'un cop en el darrer any",continuaFrequencia,riscRisc,frequenciaGravetat));			
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Abús Sexual (F.2)","La participació del nen/a i/o adolescent, adult o persona gran, en activitats sexuals que no està en condicions de comprendre, que són inapropiades per a la seva edat i el seu desenvolupament psicosexual, per a les quals és incapaç de donar el seu consentiment i que transgredeixen els tabús i les regles familiars i socials, així com les lleis vigents. En l’abús sexual existeixen dos factors clau: la coerció i la asimetria d’edat o de moment evolutiu entre les persones implicades.",entornFamiliar));		
											
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"En qualsevol situació",altaGravetat));			
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));			
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Negligència amb persones vulnerables (F.3)","Situacions en les quals les necessitats bàsiques (físiques, socials, psicològiques) d'una persona vulnerable del nucli familiar (menor, depenent, gent gran , etc.) no són ateses de manera temporal o permanent per cap dels membres del grup on conviu. Val a dir que situacions de negligència cronificada amb conseqüències greus per a la salut i el benestar de la persona que els rep s'han de considerar una forma de maltractament.",entornFamiliar));		
											
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense perill físic i l’afectació a nivell psíquic/emocional no interfereix en les competències bàsiques de la persona (autocura, comunicació, autonomia i iniciativa).",moderadaGravetat));			
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectacions greu per la integritat de la persona",altaGravetat));					
		criteriRepository.save(new Criteri ("Situació puntual",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Situació reiterada",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats per assumir responsabilitats familiars i socials (F.4)","Persones adultes que no són capaces d'assumir adequadament les seves responsabilitats en el si del nucli familiar, respecte de les persones que en depenen legalment",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," Manca de límits adequats a l’edat dels infants/adolescents/. No s’involucra activament en el desenvolupament/cura de la persona. Un altre adult assumeix les responsabilitats.Voluntat de canvi i capacitat de millora",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Manca de límits  o de cura de les persones que en depenen i ningú dins del nucli de convivència assumeix les responsabilitats. Voluntat de canvi i capacitat de millora",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No hi ha acceptació de la situació ni capacitat de millora a curt termini. Un/a menor assumeix les responsabilitats",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Estrès derivat de la situació familiar (F.5)","Situacions d'estrès derivades de canvis impactant en l'entorn familiar per situacions sobrevingudes, o com a conseqüència d'una sobrecàrrega motivada per les dinàmiques familiars.  Inclou risc de claudicació de cuidadors, si vénen motivats per un estat d'ansietat i sobrecàrrega.",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
																
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Instaurat en les dinàmiques familiars sense que  interfereixi en les activitats bàsiques.",moderadaGravetat));		
																
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectacions permanents en la cura i les responsabilitats familiars",altaGravetat));		
																
		criteriRepository.save(new Criteri ("Porta menys de 6 mesos",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Porta més de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Sobre-responsabilitat (F.6)","Membre de la unitat de convivència, adult o menor, que assumeix responsabilitats familiars que es considera que es troben per sobre de les seves possibilitats. Aquí quedaria inclòs l’embaràs adolescent Amb menors cal tenir en compte: L’activitat causant de la sobre-responsabilitat- La maduresa i edat de l’infant. - La maduresa de la família (si la família vetlla o no per minimitzar l’impacte de la responsabilitat atribuïda al menor).- Si disposa de la supervisió d’algun adult de l’entorn de proximitat.- El temps que s’exposa a la sobre-responsabilitat",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"La situació afecta a un adult amb mancances però amb capcitat de donar resposta a la situació mitjançant el suport necessari. La situació afecta a un/a menor major de 16 anys, sense que afecti greument a la seva integritat piscològica i amb possiblitat de canvis i millora.",moderadaGravetat));		
																
		criteriRepository.save(new Criteri ("Menys de 2 mesos",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Entre 2 i 6 mesos",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Més de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectació greu a menors o persones dependents. La situació afecta a un adult que no la reconeix ni accepta el suport necessari.",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));				
																
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats en les relacions familiars (F.7)","Persona que presenta dificultats de relació i comportaments agressius amb un o més membres de la unitat de convivència.",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Fets molt concrets, amb reconeixement i voluntat de canvi. No instaurat en la dinàmica familiar. Sense infants en el nucli de convivència.",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Instaurat en la dinàmica familiar però amb voluntat de canvi i millora. Amb infants en el nucli de convivència",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectació greu en la dinàmica familiar i la salut física/emocional dels seus membres",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));						
																
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de recursos personals i autonomia en la presa de decisions(F.8)","Situació en la qual la persona mostra incapacitat d'afrontar aspectes de la vida quotidiana en l'àmbit de l'organització familiar, econòmic,  laboral, de relació social, etc.",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació recent, motivada per fets externs i reversible amb  recursos personals",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));							
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació  reversible amb suport extern",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació irreversibale i amb necessitat de suport",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Continu",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
																	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca d'higiene o desordre al domicili (F.9)","Domicili que, degut al comportament d’algun del seus habitants, reuneix condicions d’habitabilitat però presenta un mal estat d'higiene general o uns elevats nivells de desordre i/o acumulació d'estris o material divers, que dificulten la vida quotidiana de les persones que hi viuen. Inclou els casos vinculats a una malaltia mental (Síndrome de Diogenes o de Noé)",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació puntual i reversible amb el suport adequat. Persones diagnosticades que col·laboren.",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situació reiterada amb necessitat de suport extern. Persones diagnosticades que no col·laboren",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Presència de menors o dependents al domicili. Greus afectacions per la integritat física de les persones. Indícis de malatia mental no diagnosticada ni en tractament",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));						
																
													
		
		Entorn entornSocial = entornRepository.save (new Entorn ("Entorn Social",ambit));
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats en les relacions socials i a la participació a la comunitat (S.1)","Persona amb dificultats per adaptar-se al seu medi social (aïllament, no respecta els límits normatius ni socials del seu entorn).",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Existeix algun xarxa relacional de suport tot i que es detecten dificultats per crear o mantenir noves relacions. Participa en alguns entorns puntuals però presenta una manca d’habilitats i capacitats per participar a la comunitat",baixaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Manca de motivació i voluntat per relacionar-se amb l’entorn comunitari. Aïllament social. Comportaments agressius en l'entorn. Els contactes són amb un entorn relacional tòxic/negatiu",moderadaGravetat));		
																
		criteriRepository.save(new Criteri ("Puntual. Conjuntural amb possibilitats de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Continu",continuaFrequencia,riscRisc,frequenciaGravetat));			
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Rebuig social (S.2)","Persona socialment aïllada i/o rebutjada per raons físiques,  de gènere, ètnia, religió, orientació sexual, conducta o qualsevol altra raó.",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Rebuig en un sol entorn i sense afectacions a la integritats física o emocional de la personal",moderadaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"En més d’un entorn. Amb afectacions a la integritat de la persona",altaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));					
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Addiccions (S.3)","Persona que fa un ús abusiu d'alguna substància tòxica i/o que presenta conductes addictives (joc, noves tecnologies, etc.), que deriven en problemes familiars, econòmics i/o laborals, i de salut.",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"En tractament i sense afectacions en les activitats essencials per la vida diària ",baixaGravetat));		
																	
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense tractar o No compliment de les pautes de tractament. Afectacions no interfereixen en les activitats essencials de la vida diària o les responsabilitats familiars  ",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("De 1 a 3 vegades per setmana",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Més de 3 vegades per setmana",puntualFrequencia,alttRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Abús sever i afectació important en les activitats essencials de la vida diària o les responsabilitats familiars",altaGravetat));		
																	
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));				
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Desatenció institucional  (S.4)","Persones que per la seva situació legal o adminsitrativa no tenen accés a determinats recursos de l'àmbit de  salut, educació i protecció social.",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense valoració",altaGravetat));		
														
		criteriRepository.save(new Criteri ("Capacitat de resolució / legalització",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Incapacitat de resolució ",continuaFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Activitats marginals (S.5)","Treball no legalitzat, activitats marginals (Prostitució, venda ambulant il·lega, recollida de ferralla, altres).",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Treball estable, sense coercions ni situacions de risc greu per la persona. Contacte esporàdic amb la policia i/o mesures penals pendent",moderadaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoració",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Sense valoració",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Les activitats marginals comporten un alt risc (salut física i emocianal, delictes penals) per la persona.",altaGravetat));		
																	
		criteriRepository.save(new Criteri ("Sense valoració",senseFrequencia,alttRisc,frequenciaGravetat));	

		
		
		// Proteccio
		
		Factor factor;
		
		factor=factorRepository.save(new Factor ("La malaltia causa del dèficit d'autonomia està diagnosticada i en tractament"));
		
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		
		factor=factorRepository.save(new Factor ("La persona té reconeguts el grau de discapacitat i / o dependència"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona és usuària d'un servei, recurs o prestació  en funció de la seva malaltia, discapacitat o situació de dependència"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("Hi ha una xarxa familiar i / o social de suport implicada en la cura"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona segueix amb regularitat el tractament mèdic i/o té un hàbits saludables per la seva situació de salut"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));

		// Risc
		
		factor=factorRepository.save(new Factor ("Es preveu que el dèficit d’autonomia de la persona s’agreugi en un curt termini (durant l’any)"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La malaltia causa del dèficit d'autonomia no està diagnosticada i en tractament"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona no segueix amb regularitat el tractament mèdic prescrit i/o no té uns hàbits saludables per la seva situació de salut"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("No existeix una xarxa familiar i / o social que s’impliqui en la cura"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("Cuidador / a habitual en risc o en situació de claudicació"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La situació i/o característiques del domicili agreugen el dèficit d’autonomia  de les persones que hi viuen"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona amb dèficit d’autonomia té responsabilitats/carregues familiars"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		
		
		// Proteccio
		factor=factorRepository.save(new Factor ("Una o més persones de la família disposa d'ingressos estables (pensions, prestacions, rendes, etc.)."));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Una o més persones de la família disposa d'estalvis"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Una o més persones de la família disposa de patrimoni a part de la primera residència"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Una o més persones de la família disposa de treball estable."));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Existeix xarxa familiar o relacional disposada a oferir suport econòmic"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("En cas de separació o divorci hi ha conveni regulador i es compleixen els seus termes"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("La persona té una bona predisposició per formar-se i/o treballar"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Es disposa d’un habitatge habitual pel qual no s’ha d’assumir cap despesa o és un habitatge social"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));


		// Risc
		factor=factorRepository.save(new Factor ("Existeix un impagament de pensions de manutenció i la corresponent demanda judicial"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Existeixen responsabilitats i càrregues familiars que agreugen la manca de recursos materials/instrumentals"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Hi ha un endeutament creixent i inassolible amb els recursos disponibles"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEconomic));
		
		
		// Proteccio
		factor=factorRepository.save(new Factor ("La persona mostra una vinculació relacional positiva amb la família extensa o les seves xarxes d’amics i relacions"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("La persona està vinculada i participa activament amb la comunitat, entitats o associacions del seu entorn"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("Existeix xarxa familiar o relacional disposada a oferir suport i acompanyament"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'infància que el nen / a manté relació afectiva amb els adults de referència i iguals"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'infància que el nen / a mostra una autonomia personal adequada per a la seva edat"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'infància que el nen / a participa en activitats fora de l'entorn familiar o escolar"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("La persona/família és usuària de recursos/serveis adreçats a cobrir la necessitat en l’àmbit relacional (escolar, familiar o comunitari) "));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));


		// Risc
		factor=factorRepository.save(new Factor ("Existeix una xarxa social (veïns, amics, família extensa..) que exerceix una influència negativa"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("Sospita o malaltia mental diagnosticada que agreugen la situació "));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("Existeix una problemàtica de salut no diagnosticada que agreuja la situació"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'infància que el nen / a no manté relació afectiva amb els adults de referència i iguals"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'infància que el nen / a no mostra una autonomia personal adequada per a la seva edat"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'infància que el nen / a no participa en activitats fora de l'entorn familiar o escolar"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		
		

		factorEconomicRepository.save(new FactorEconomic("Pagar les factures d'habitatge o serveis públics."));
		factorEconomicRepository.save(new FactorEconomic("Mantenir la llar adequadament calenta."));
		factorEconomicRepository.save(new FactorEconomic("Assumir despeses inesperades."));
		factorEconomicRepository.save(new FactorEconomic("Menjar carn o les proteïnes de forma regular."));
		factorEconomicRepository.save(new FactorEconomic("Anar de vacances."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de cotxe."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de rentadora."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de connexió a internet."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de telèfon mòbil"));
		
		ambit = ambitRepository.save(new Ambit ("Globalitat del cas"));
		
		Entorn entorn = entornRepository.save (new Entorn ("Globalitat del cas",ambit));
		
		factor=factorRepository.save(new Factor ("La persona i / o família té consciencia i accepta  les dificultats / malaltia causants de la seva situació"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona i / o família accepta el suport i l'orientació professional"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona coneix i té una actitud proactiva i participativa envers el sistema i les xarxes de protecció"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona i / o família no té consciencia i/o no accepta  les dificultats/malaltia causants de la seva situació"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona i / o família no accepta el suport i l'orientació professional"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona no té una actitud activa i ni participativa envers el sistema i les xarxes de protecció i/o té una posicionament victimista respecte els sistemes de protecció"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("Composició familiar vulnerable (monoparental, famílies nombroses de categoria especial)"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		
		
		if (addTest) {
			test();
		}
		
	}
	
	
	private void test () {
		
		log.info ("===========    ADD TEST    ==========");
		
		personaRepository.save(new Persona ("FULANO","APE1","APE2"));
	}
}
