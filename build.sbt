name := """playing-slick-pg"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(jdbc, cache, ws)

libraryDependencies ++= Seq(
	"com.typesafe.play" 	%% "play-jdbc" 			% "2.3.1",
    "com.typesafe.play" 	%% "play-json" 			% "2.3.1",
    "com.typesafe.play" 	%% "play-slick" 		% "0.8.1",
    "com.github.tminglei" 	%% "slick-pg" 			% "0.8.1",
  	"org.webjars" 			%% 	"webjars-play" 		% "2.3.0-2",
  	"org.webjars" 			%	"bootstrap" 		% "3.1.1-2",
  	"org.webjars" 			% 	"bootswatch-cosmo" 	% "3.3.1+2",
  	"org.webjars" 			% 	"html5shiv" 		% "3.7.0",
  	"org.webjars" 			% 	"respond" 			% "1.4.2"
)

