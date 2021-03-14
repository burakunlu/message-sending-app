SHELL := /bin/bash
.DEFAULT_GOAL := help

# And add help text after each target name starting with '\#\#'
help: ## Options
        @echo 'usage: make [target]'
        @echo
        @echo 'target:'
        @echo -e "$$(grep -hE '^\S+:.*##' $(MAKEFILE_LIST) | sed -e 's/:.*##\s*/:/' -e 's/^\(.\+\):\(.*\)/\\x1b[36m\1\\x1b[m:\2/' | column -c2 -t -s :)"

build: ## Build user app
        mvn clean install

run: ## Run user app
        java -jar target/message-sending-app-1.0.0-SNAPSHOT.jar $(TYPE) $(FIRST_MESSAGE) $(USER_NAME)