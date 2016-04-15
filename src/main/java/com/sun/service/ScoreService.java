package com.sun.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sun.domain.ScoreVO;
import com.sun.persistence.RDAO;

@Service
public class ScoreService {

	@Inject
	private RDAO dao;
	
	public void createScoreTable(long mem_snum) {
		dao.createScoreTable(mem_snum);
	}
	
	public void calScore(String mem_id, long mem_snum) {
		ScoreVO score = new ScoreVO();
		long education=0, IT=0, game=0, fashion=0, daily=0;
		long health=0, news=0, travel=0, sports=0, food=0;
		long music=0, movie=0, book=0, car=0, pet=0;
		long nature=0, goods=0, beauty=0, cartoon=0, love=0;
		List<String> queryboard = dao.selectqueryboard(mem_id);
		List<String> likelist = dao.selectlikelist(mem_id);
		List<String> commentlist = dao.selectcommentlist(mem_id);
		for(int i=0;i<queryboard.size();i++) {
			String category = queryboard.get(i);
			if(category.equals("education")) {
				score.setEducation(++education);
			}
			else if(category.equals("IT")) {
				score.setIT(++IT);
			}
			else if(category.equals("game")) {
				score.setGame(++game);
			}
			else if(category.equals("fashion")) {
				score.setFashion(++fashion);
			}
			else if(category.equals("daily")) {
				score.setDaily(++daily);
			}
			else if(category.equals("health")) {
				score.setHealth(++health);
			}
			else if(category.equals("news")) {
				score.setNews(++news);
			}
			else if(category.equals("travel")) {
				score.setTravel(++travel);
			}
			else if(category.equals("sports")) {
				score.setSports(++sports);
			}
			else if(category.equals("food")) {
				score.setFood(++food);
			}
			else if(category.equals("music")) {
				score.setMusic(++music);
			}
			else if(category.equals("movie")) {
				score.setMovie(++movie);
			}
			else if(category.equals("book")) {				
				score.setBook(++book);
			}
			else if(category.equals("car")) {
				score.setCar(++car);
			}
			else if(category.equals("pet")) {
				score.setPet(++pet);
			}
			else if(category.equals("nature")) {
				score.setNature(++nature);
			}
			else if(category.equals("goods")) {
				score.setGoods(++goods);
			}
			else if(category.equals("beauty")) {
				score.setBeauty(++beauty);
			}
			else if(category.equals("cartoon")) {
				score.setCartoon(++cartoon);
			}
			else if(category.equals("love")) {
				score.setLove(++love);
			}
		}
		
		for(int i=0;i<likelist.size();i++) {
			String category = likelist.get(i);
			if(category.equals("education")) {
				education += 3;
				score.setEducation(education);
			}
			else if(category.equals("IT")) {
				IT += 3;
				score.setIT(IT);
			}
			else if(category.equals("game")) {
				game += 3;
				score.setGame(game);
			}
			else if(category.equals("fashion")) {
				fashion += 3;
				score.setFashion(fashion);
			}
			else if(category.equals("daily")) {
				daily += 3;
				score.setDaily(daily);
			}
			else if(category.equals("health")) {
				health += 3;
				score.setHealth(health);
			}
			else if(category.equals("news")) {
				news += 3;
				score.setNews(news);
			}
			else if(category.equals("travel")) {
				travel += 3;
				score.setTravel(travel);
			}
			else if(category.equals("sports")) {
				sports += 3;
				score.setSports(sports);
			}
			else if(category.equals("food")) {
				food += 3;
				score.setFood(food);
			}
			else if(category.equals("music")) {
				music += 3;
				score.setMusic(music);
			}
			else if(category.equals("movie")) {
				movie += 3;
				score.setMovie(movie);
			}
			else if(category.equals("book")) {
				book += 3;
				score.setBook(book);
			}
			else if(category.equals("car")) {
				car += 3;
				score.setCar(car);
			}
			else if(category.equals("pet")) {
				pet += 3;
				score.setPet(pet);
			}
			else if(category.equals("nature")) {
				nature += 3;
				score.setNature(nature);
			}
			else if(category.equals("goods")) {
				goods += 3;
				score.setGoods(goods);
			}
			else if(category.equals("beauty")) {
				beauty += 3;
				score.setBeauty(beauty);
			}
			else if(category.equals("cartoon")) {
				cartoon += 3;
				score.setCartoon(cartoon);
			}
			else if(category.equals("love")) {
				love += 3;
				score.setLove(love);
			}
		}
		
		for(int i=0;i<commentlist.size();i++) {
			String category = commentlist.get(i);
			if(category.equals("education")) {
				education += 2;
				score.setEducation(education);
			}
			else if(category.equals("IT")) {
				IT += 2;
				score.setIT(IT);
			}
			else if(category.equals("game")) {
				game += 2;
				score.setGame(game);
			}
			else if(category.equals("fashion")) {
				fashion += 2;
				score.setFashion(fashion);
			}
			else if(category.equals("daily")) {
				daily += 2;
				score.setDaily(daily);
			}
			else if(category.equals("health")) {
				health += 2;
				score.setHealth(health);
			}
			else if(category.equals("news")) {
				news += 2;
				score.setNews(news);
			}
			else if(category.equals("travel")) {
				travel += 2;
				score.setTravel(travel);
			}
			else if(category.equals("sports")) {
				sports += 2;
				score.setSports(sports);
			}
			else if(category.equals("food")) {
				food += 2;
				score.setFood(food);
			}
			else if(category.equals("music")) {
				music += 2;
				score.setMusic(music);
			}
			else if(category.equals("movie")) {
				movie += 2;
				score.setMovie(movie);
			}
			else if(category.equals("book")) {
				book += 2;
				score.setBook(book);
			}
			else if(category.equals("car")) {
				car += 2;
				score.setCar(car);
			}
			else if(category.equals("pet")) {
				pet += 2;
				score.setPet(pet);
			}
			else if(category.equals("nature")) {
				nature += 2;
				score.setNature(nature);
			}
			else if(category.equals("goods")) {
				goods += 2;
				score.setGoods(goods);
			}
			else if(category.equals("beauty")) {
				beauty += 2;
				score.setBeauty(beauty);
			}
			else if(category.equals("cartoon")) {
				cartoon += 2;
				score.setCartoon(cartoon);
			}
			else if(category.equals("love")) {
				love += 2;
				score.setLove(love);
			}
		}		
		
		dao.updatescore(mem_snum, score);
	}
}
