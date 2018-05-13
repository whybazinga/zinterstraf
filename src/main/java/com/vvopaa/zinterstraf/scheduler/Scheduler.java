package com.vvopaa.zinterstraf.scheduler;

import com.vvopaa.zinterstraf.service.ProTeamService;
import com.vvopaa.zinterstraf.service.UserService;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class Scheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
  private static final String PRO_TEAMS_URL = "http://www.dota2.com/procircuit";
  private static final String ID_TEAMS_BODY = "teamsBody";
  private static final String CLASS_TEAMS_ELEMENT = "teamsElement";
  private static final String CLASS_COLUMN_CONTENT = "columnContent";
  private static final String CLASS_COLUMN_REDUNDANT = "rosterColumn";

  @Autowired
  private ProTeamService proTeamService;

  private Map<String, List<S>>

  public Scheduler() {

  }

  @Scheduled(cron = "0 15 10 15 * ?")
  public void scheduleTaskUsingCronExpression() throws IOException {
    try {
      Document doc = Jsoup.connect(PRO_TEAMS_URL).get();
      doc.getElementById(ID_TEAMS_BODY).getElementsByClass(CLASS_TEAMS_ELEMENT).forEach((teamElement)-> {

        Optional<Elements> element = Optional.of(teamElement.getElementsByClass(CLASS_COLUMN_REDUNDANT));
        element.ifPresent(Elements::remove);

        teamElement.getElementsByClass(CLASS_COLUMN_CONTENT).forEach((columnElement)-> {
          columnElement.getE
        });
      });




    } catch (HttpStatusException httpExc) {
      LOGGER.warn(httpExc.getMessage());
    }


  }


}
