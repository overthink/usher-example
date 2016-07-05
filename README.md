# usher-example

A very small [Usher](https://github.com/overthink/usher) example app.

## HOWTO

1. `sbt run`
1. Point browser to http://localhost:8888

```
$ curl http://localhost:8888
Usher demo
$ curl http://localhost:8888/saying/0
Am I a good person? No. But do I try to be better every single day? Also no - @internethippo
$ curl http://localhost:8888/saying/1
Remember, always follow your passion. And if your passion doesn't fit into global capitalism, well, then you are a failure at life. - @existentialcoms
$ curl http://localhost:8888/saying/2
A heuristic on whether you have control of your life: can you take naps? - @nntaleb
$ curl http://localhost:8888/saying/3
Computer said no.
$ curl -XPOST -dtest http://localhost:8888/saying
$ curl http://localhost:8888/saying/3
test
$ curl -XPUT -d "here's something different"  http://localhost:8888/saying/3
$ curl http://localhost:8888/saying/3
here's something different
```

## License

Copyright &copy; 2016 Mark Feeney

Released under the MIT license.
