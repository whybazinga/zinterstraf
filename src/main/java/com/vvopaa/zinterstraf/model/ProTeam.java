package com.vvopaa.zinterstraf.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pro_teams")
public class ProTeam extends AbstractEntity {

  @Column(name="rank")
  private String rank;

  @Column(name="img")
  private String img;

  @Column(name="name")
  private String name;

  @Column(name="earnings")
  private String earnings;

  @Column(name="points")
  private String points;

  @Column(name="players")
  private String players;

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEarnings() {
    return earnings;
  }

  public void setEarnings(String earnings) {
    this.earnings = earnings;
  }

  public String getPoints() {
    return points;
  }

  public void setPoints(String points) {
    this.points = points;
  }

  public String getPlayers() {
    return players;
  }

  public void setPlayers(String players) {
    this.players = players;
  }
}
