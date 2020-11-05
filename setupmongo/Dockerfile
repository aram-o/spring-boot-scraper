FROM mongo:4

COPY setup.sh .
COPY dbSetup.js .

RUN ["chmod", "+x", "./setup.sh"]
CMD ["./setup.sh"]


