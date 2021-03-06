################################################################################
#     File Name           :     makefile                                       #
#     Created By          :     Klas Segeljakt                                 #
#     Creation Date       :     [2017-04-18 15:37]                             #
#     Last Modified       :     [2017-09-23 14:40]                             #
#     Description         :     Makefile for pandoc.                           #
################################################################################
DOC=README
##################################### DOC ######################################
DOCC=pandoc
#-------------------------------------------------------------------------------
SRC_FMT=md
DST_FMT=pdf
#-------------------------------------------------------------------------------
SRC_PATH=$(DOC).$(SRC_FMT)
DST_PATH=$(DOC).$(DST_FMT)
STL_PATH=/Users/Klas/Git/writing/styles/ieee-with-url.csl
BIB_PATH=~/.vim/bibtex/library.bib
TEM_PATH=/Users/Klas/Git/writing/markdown.latex
FIL_PATH=/Users/Klas/Git/writing/markdown-filters
# Flags
#DOC_FLAGS += --listings
#DOC_FLAGS += --filter pandoc-tablenos
#DOC_FLAGS += --filter pandoc-fignos
#DOC_FLAGS += --lua-filter $(FIL_PATH)/short-captions/short-captions.lua
COUNT_FILTER =  --lua-filter $(FIL_PATH)/wordcount/wordcount.lua
DOC_FLAGS += --bibliography $(BIB_PATH)
DOC_FLAGS += --csl=$(STL_PATH)
#DOC_FLAGS += --template /Users/Klas/Git/writing/default.latex
DOC_FLAGS += --template $(TEM_PATH)
DOC_FLAGS += -N
#DOC_FLAGS += -mathml
#DOC_FLAGS += --pdf-engine=lualatex
DOC_FLAGS += -F pandoc-crossref
DOC_FLAGS += -F pandoc-citeproc
DOC_FLAGS += -V monofont=sourcecodeprobold
DOC_FLAGS += --include-in-header=testest.latex
#-------------------------------------------------------------------------------
all:
	echo > README.md
	cat chapters/0-*.md >> README.md
	cat chapters/1-*.md >> README.md
	cat chapters/2-*.md >> README.md
	cat chapters/3-*.md >> README.md
	cat chapters/4-*.md >> README.md
	cat chapters/5-*.md >> README.md
	cat chapters/6-*.md >> README.md
	cat chapters/7-*.md >> README.md
	cat chapters/8-*.md >> README.md
	cat chapters/9-*.md >> README.md
	cat chapters/10-*.md >> README.md
	$(DOCC) $(SRC_PATH) $(DOC_FLAGS) -o $(DST_PATH)
header:
	echo > README.md
	cat chapters/0-*.md >> README.md
	$(DOCC) $(SRC_PATH) $(DOC_FLAGS) -o $(DST_PATH)
#################################### CLEAN #####################################
count:
	$(DOCC) $(SRC_PATH) $(DOC_FLAGS) $(COUNT_FILTER) -o $(DST_PATH)
