def q = new registertest.Quote(author:'hopkins2',statement:'not again')
q.save()

registertest.Quote.list().each { println "${it.author} ${it.statement} ${it.id}" }