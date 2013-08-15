package org.sikuli.slides.api.models;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.sikuli.slides.api.interpreters.Keyword;
import org.sikuli.slides.api.interpreters.Selector;

import com.google.common.collect.Lists;

public class Slide {
	
	List<SlideElement> elements = Lists.newArrayList();
	private int number; // one-based
	
	
	// Construct a blank slide
	public Slide(){		
	}
	
	// Construct a new slide by copying the content from a given slide
	public Slide(Slide slide){
		elements = Lists.newArrayList(slide.elements);
		number = slide.number;
	}
		
	public void add(SlideElement element){
		elements.add(element);
	}
	
	public void remove(SlideElement element){
		elements.remove(element);
	}
	
	public Collection<SlideElement> getElements(){
		return Lists.newArrayList(elements);		
	}

	public String toString(){
		String txt = "";
		for (SlideElement el : elements){
			txt = txt + el.toString() + "\n";
		}
		return txt;
	}
	
	public Selector select(){
		return Selector.select(this);
	}
	
	public SlideElementBuilder newElement() {		
		return new SlideElementBuilder(this);
	}
	
	public ImageElementBuilder newImageElement() {		
		return new ImageElementBuilder(this);
	}
	
	public KeywordElementBuilder newKeywordElement() {		
		return new KeywordElementBuilder(this);
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public static class KeywordElementBuilder extends SlideElementBuilder {
		KeywordElementBuilder(Slide slide){
			super(slide);
			this.element = new KeywordElement();
		}

		public KeywordElementBuilder keyword(Keyword keyword){
			((KeywordElement)element).setKeyword(keyword);
			return this;
		}
	}
	
	public static class ImageElementBuilder extends SlideElementBuilder {
		ImageElementBuilder(Slide slide){
			super(slide);
			this.element = new ImageElement();
		}

		public ImageElementBuilder source(URL source) {
			((ImageElement)element).setSource(source);
			return this;
		}
	}
	
	public static class SlideElementBuilder{
		SlideElement element;	
		protected Slide slide;
		SlideElementBuilder(Slide slide){
			this.slide = slide;
			this.element = new SlideElement();
		}		
		public SlideElementBuilder text(String text){
			element.setText(text);
			return this;
		}
		public SlideElementBuilder bounds(int x, int y, int w, int h) {
			element.setOffx(x);
			element.setOffy(y);
			element.setCx(w);
			element.setCy(h);
			return this;
		}
		public SlideElement add(){
			slide.add(element);
			return element;
		}		
	}
}