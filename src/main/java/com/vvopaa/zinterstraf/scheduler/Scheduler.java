package com.vvopaa.zinterstraf.scheduler;

import com.vvopaa.zinterstraf.model.DomBuilder;
import com.vvopaa.zinterstraf.model.ProPlayer;
import com.vvopaa.zinterstraf.model.ProTeam;
import com.vvopaa.zinterstraf.service.ProTeamService;
import com.vvopaa.zinterstraf.service.UserService;
import com.vvopaa.zinterstraf.util.FunctionUtil;
import com.vvopaa.zinterstraf.util.LoggerUtil;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class Scheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
  private static final String PRO_TEAMS_URL = "http://www.dota2.com/procircuit";
  private static final String CLASS_COLUMN = "columnElement";

  private static final String ID_TEAMS_BODY = "teamsBody";
  private static final String CLASS_TEAMS = "teamsElement";

  private static final String ID_SCHEDULE_BODY = "teamsBody";
  private static final String CLASS_SCHEDULE = "teamsElement";

  private static final String ID_PLAYERS_BODY = "teamsBody";
  private static final String CLASS_PLAYERS = "teamsElement";


  @Autowired
  private ProTeamService proTeamService;


  @Scheduled(cron = "0 15 10 15 * ?")
  public void cronProCircuit() throws IOException {
    try {
      Document doc = Jsoup.connect(PRO_TEAMS_URL).get();

      Thread teamsThread = new Thread(() -> {
        List<ProTeam> teams = new ArrayList<>();
        doc.getElementById(ID_TEAMS_BODY).getElementsByClass(CLASS_TEAMS).forEach(teamElement-> {
          ProTeam.ProTeamBuilder teamBuilder = new ProTeam.ProTeamBuilder();
          teamElement.getElementsByClass(CLASS_COLUMN).forEach(teamBuilder::buildByDomElements);
          teams.add(teamBuilder.build());
        });

        //TODO
        List<ProPlayer> playersWithoutTeam = new ArrayList<>();
        doc.getElementById(ID_PLAYERS_BODY).getElementsByClass(CLASS_PLAYERS).forEach(playerElement-> {
          ProPlayer.ProPlayerBuilder playerBuilder = new ProPlayer.ProPlayerBuilder();
          playerElement.getElementsByClass(CLASS_COLUMN).forEach(playerBuilder::buildByDomElements);
          ProPlayer player = playerBuilder.build();

          Optional<ProTeam> optTeam = teams.stream().filter(s->s.getName().equals(player.getTeamName())).findFirst();
          optTeam.isPresent() ? 

          FunctionUtil.getInstance().runFunctionIfTrue(v.getName().equals(), () -> this.proTeam = proTeam);


            /*
            teams.forEach((k,v)-> {
              FunctionUtil.getInstance().runFunctionIfTrue(v.getName().equals(), () -> this.proTeam = proTeam);
            }
            */
        });
      });
      teamsThread.start();





      /*
      Thread scheduleThread = new Thread(() -> {
        ProTeam.ProTeamBuilder teamBuilder = new ProTeam.ProTeamBuilder();
        doc.getElementById(ID_SCHEDULE_BODY).getElementsByClass(CLASS_SCHEDULE).forEach(teamElement->
          teamElement.getElementsByClass(CLASS_COLUMN).forEach(teamBuilder::buildByDomElements));
        teamBuilder.build();
      });
      */
    } catch (HttpStatusException httpExc) {
      LOGGER.warn(httpExc.getMessage());
    } finally {
      LOGGER.info(LoggerUtil.logClassFunctionInfo(Scheduler.class));
    }

  }

  private <T extends DomBuilder> T getReadyClassBuilder(Class<T> builderClass) throws IllegalAccessException, InstantiationException {
    T test = builderClass.newInstance();
    return null;
  }
}
