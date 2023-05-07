JAVAC  = javac 
BINDIR = bin
SRCDIR = src


.SUFFIXES: .java .class


$(BINDIR)/typingTutor/%.class:$(SRCDIR)/*/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<


CLASSES = WordDictionary.class HungryWord.class FallingWord.class Score.class ScoreUpdater.class\
			HungryWordMover.class WordMover.class CatchWord.class GamePanel.class TypingTutorApp.class
			
CLASS_FILES = $(CLASSES:%.class=$(BINDIR)/typingTutor/%.class)

default: $(CLASS_FILES)


clean:
	rm -r $(BINDIR)


run:
	java -cp bin typingTutor.TypingTutorApp
