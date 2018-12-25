package com.vvopaa.zinterstraf.model;

import com.vvopaa.zinterstraf.util.FunctionUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ProSchedule extends AbstractEntity {

  @Column(unique = true)
  private String dateFromTo;

  @Column
  private String status;

  @Column
  private String country;

  @Column
  private String prize;

  @Column
  private Integer points;

  @Column
  private String organizerImgUrl;

  private ProSchedule(ProScheduleBuilder builder) {
    this.dateFromTo = builder.dateFromTo;
    this.status = builder.status;
    this.country = builder.country;
    this.prize = builder.prize;
    this.points = builder.points;
    this.organizerImgUrl = builder.organizerImgUrl;
  }

  @Override
  public String toString() {
    return "ProSchedule{" +
      "status='" + status + '\'' +
      '}';
  }

  public static class ProScheduleBuilder implements DomBuilder {
    private static final String CLASS_DATE = "dateColumn";
    private static final String CLASS_STATUS = "statusColumn";
    private static final String CLASS_COUNTRY = "locationColumn";
    private static final String CLASS_PRIZE = "prizeColumn";
    private static final String CLASS_POINTS = "pointsColumn";
    private static final String CLASS_ORGANIZER = "organizerColumn";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProSchedule.ProScheduleBuilder.class);

    private String dateFromTo;
    private String status;
    private String country;
    private String prize;
    private Integer points;
    private String organizerImgUrl;


    @Override
    public void buildByDomElements(Element domEl) {
      try {
        FunctionUtil.getInstance()
          .runFunctionIfTrue(domEl.hasClass(CLASS_DATE), () -> this.dateFromTo = domEl.text())
          .runFunctionIfTrue(domEl.hasClass(CLASS_STATUS), () -> this.status = domEl.text())
          .runFunctionIfTrue(domEl.hasClass(CLASS_COUNTRY), () -> this.country = domEl.text())
          .runFunctionIfTrue(domEl.hasClass(CLASS_PRIZE), () -> this.prize = domEl.text())
          .runFunctionIfTrue(domEl.hasClass(CLASS_POINTS), () -> this.points = Integer.parseInt(domEl.text()))
          .runFunctionIfTrue(domEl.hasClass(CLASS_ORGANIZER), () -> this.organizerImgUrl = domEl.children().get(0).attr(DomBuilder.SRC_ATTRIBUTE));
      } catch (NullPointerException ex) {
        LOGGER.info(DomBuilder.TXT_WRITTEN);
      }
    }

    @Override
    public ProSchedule build() {
      return new ProSchedule(this);
    }
  }
}
