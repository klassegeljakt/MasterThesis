\clearpage

# Programming Languages

A programming language is a tool for communicating with computers [@CraftingInterpreters]. Although programming languages can have vastly different designs, their implementations, to some degree, follow the same structure. Each program starts off as source code and is analyzed and transformed progressively in a series of stages. Compilers typically divide the stages into three components, a front-end, optimizer, and back-end [@Universal].

## Front-end

The front-end statically verifies the lexical, syntactic and semantic correctness of the program. These are analogous to verifying that sentences in natural language contain correctly spelled words, are grammatically correct, and have a sensible meaning. Simultaneously, the front-end will transform the program into an IR which is at a higher level abstraction and is easier to work with.

### Lexical analysis

First, source code is lexed, i.e., scanned, into a flat stream of tokens according to a regular expression. The source code is composed of whitespace and lexemes, i.e., lexical units, which are the words of the programming language [@LexicalStructure]. Some languages have a pre-processor which operates before the lexer. The pre-processor may alter the source code through macro expansion and various directives [@JavaCompiler]. Tokens are categories of lexemes, e.g., identifiers, keywords, literals, separators and operators. These resemble nouns, verbs, and adjectives, etc. The regular expression could be viewed as a lexicon or vocabulary which defines tokens and lexemes for a regular language. Some tokens, e.g., identifiers and literals, also have an associated semantic value.

### Syntactic analysis

After scanning, tokens are parsed into a parse tree according to a grammar. The parse tree describes the concrete syntactic structure of the program. Nodes in the tree are syntactic units, referred to as symbols. Each symbol is classified as either terminal or non-terminal. While terminals are tokens, non-terminals are symbols which may be substituted by zero or more symbols and are thereby analogous to phrases. The grammar is in the form of production rules which dictate which symbols substitute other symbols. Grammars for programming languages are in general context-free. In other words, how a symbol is parsed does not depend on its relative position to other symbols. In contrast, only a subset of the natural languages are assumed to have context-free grammars [@ContextSensitive]. History has shown however that the dissimilarity between programming languages and natural languages is decreasing as programming languages are becoming increasingly more high level [@ChalmersCompiler].

A parser can be implemented by hand with a parser combinator or be generated with a parser generator [@ChalmersCompiler, ch. 3]. Parser combinators are DSLs for constructing backtracking recursive descent parsers which operate at runtime. In contrast, parser generators generate a parser according to a context-free grammar, e.g., Backus-Naur form (BNF) grammar. Generated parsers can sometimes be more restrictive than parser combinators, but are more predictable, and offer greater performance. In addition, some parser generators can generate to multiple target languages.

After parsing, the parse tree is converted into an abstract syntax tree (AST) [@ChalmersCompiler, pp. 33]. The AST excludes needless information such as the appearance and ordering of symbols. Although each language parses to a different AST, the concept of a unified AST (UAST) across different languages and paradigms has been explored in the field of code refactoring [@UAST]. Languages would parse into separate ASTs and then combine into a UAST which enables cross-language optimizations. The idea has been put into practice in the ParaPhrase project which coalesces Erlang, C and C++ under a UAST. ParaPhrase then applies cross-language refactorings to the UAST, introducing parallel programming patterns to sequential code.

### Semantic analysis

The remaining stages of the front-end concern the program's semantics [@CraftingInterpreters]. The compiler has to determine the meaning of the program and verify if it is sensible. What constitutes as semantics varies between languages. Most languages start with *name-binding* which binds each symbol with an identifier to the site where the identifier was introduced. Another central part of semantic analysis is often *type checking*, which is about catching inconsistencies in the program. Type checking involves verifying that each operator has matching operands, to for example prevent an integer from being multiplied with a string [@DragonBook]. In some languages, it is however possible to add values of different types by coercing, i.e., implicitly converting, one type to the other. The set of rules types must conform to are defined by the *type system*. These rules are sometimes notated as *inference rules*, which draw *conclusions* from *premises*. A conclusion or premise is a *judgment* in the form of *e: T*, meaning *e* has type *T* [@ChalmersCompiler]. For example, given the premises *x: float* and *y: float*, it can be concluded that *x+y: float*. Thus, given an expression *e* and a type *T*, type checking must decide whether *e: T*. Some type systems also support type inference, which is about finding a type *T* for *e* such that *e: T*. How a type is inferred depends on how it is used. For example, if *x: float* and *x+y: int*, then *y: int* could be a possible solution. The isomorphism between type systems and logic systems is referred to as the Curry-Howard isomorphism [@CurryHoward]. Type systems derive two properties from logic systems, *soundness* and *completeness* [@SoundnessCompleteness]. A sound type system will reject programs which are not type safe. A complete type system will not reject programs which are type safe. The former prevents false negatives (misses) and the latter false positives (false alarms). Type systems of modern languages are sound but not complete [@BadSource]. If a type system is unsound for some property and moves the responsibility for checking it to the programmer, then it is weakly typed. Conversely, if a type system is statically sound, or is unsound for some property but employs dynamic checks to prevent it, then it is strongly typed. Some compilers store type information about AST symbols in a *symbol table* [@CraftingInterpreters]. The parser can also leave empty fields in the AST which get filled in later, known as *attributes*. Languages without type checking trade type errors for runtime errors and are called *untyped* languages.

## Optimizer

Optimizations commence when the program is at a sufficiently high level of abstraction [@CraftingInterpreters]. The optimizer applies various target-independent optimizations to the IR such as loop unrolling, dead-code elimination (DCE), and common sub-expression elimination (CSE). Loop unrolling unrolls a loop into a larger loop body with fewer iterations, allowing for better instruction scheduling [@DragonBook, ch. 9]. DCE removes code that computes values which never gets used. CSE locates expressions that evaluate to the same value, and substitutes them with a common variable only needing to be computed once. Certain optimizations have a space-time tradeoff. For example, loop unrolling produces faster code but also increases the executable size.

## Back-end

Finally, the back-end synthesizes machine code for a specific architecture [@CraftingInterpreters]. Multiple programming languages re-use the same back-end through a shared IR. Another option is to *transpile* the program. Instead of generating IR code targeting some back-end, transpilers generate code for some other programming language. Afterwards, a compiler for the target language may compile the generated code. Another approach is to *interpret*, i.e., directly execute, the program. Some interpreters execute by recursively traversing the AST, these are referred to as tree-walk interpreters. Certain compilers produce hardware-independent byte code instead, of hardware-dependent machine code, which gets interpreted by a *virtual machine* (VM). Some VMs support just-in-time (JIT) compilation where byte-code is compiled down to machine code just before being executed. This is combined with profiling, i.e., inspecting the code as it is being run, to allow for runtime optimizations. Ahead-of-time compilation only synthesizes code once, i.e., without runtime optimizations.

## Domain Specific Languages (DSL)

<!--What are Domain Specific Languages?--> There are two categories of programming languages: General Purpose Languages (GPLs) and Domain Specific Languages (DSLs). DSLs are small languages suited to interfacing with a specific problem domain [@FoldingDSL] and often act as a complement to GPLs. In contrast, GPLs are designed for a wide variety of problem domains. GPLs are, unlike DSLs, always Turing complete. Therefore, anything that can be programmed in a DSL can also be programmed in a GPL. The opposite may not always apply. Using DSLs can nevertheless lighten the burden of solving specific problems. For example, SQL is convenient for writing search queries on relational data. By being restricted to a certain problem domain, DSLs can offer high level abstractions without sacrificing performance. DSLs are also capable of aggressive domain-specific optimizations.

<!--External and Embedded DSLs--> A DSL can either be *external* or *embedded*. External DSLs exist in their own ecosystem, with a custom compiler, debugger, editor, etc. Building and maintaining these tools can be cumbersome. In contrast, embedded DSLs reside within a host GPL as a library or framework. As a result, they take less time to develop, but are restricted in their expressiveness by the host GPL's syntax and semantics.

<!--Shallow and Deep Embedding--> Embedded DSLs can either have a *shallow* or *deep* embedding [@FoldingDSL][@EDSL]. A shallow embedding implicates the DSL is executed eagerly without constructing an IR. A deep embedding means the DSL creates an IR which can be interpreted in multiple ways, e.g., generated, optimized, compiled, and checked. The host GPL acts as a metalanguage and the DSL as an object language. The metalanguage is able to re-shape the object language, since it is only data.

A powerful IR is higher order abstract syntax (HOAS), which is a generalization of the ordinary first-order abstract syntax (FOAS) trees, i.e., ASTs, found in compilers [@HOAS]. In FOAS, nodes refer to each other through the use of symbolic identifiers. HOAS extends FOAS by capturing name binding information. Hence, nodes in HOAS directly refer to each other with links, forming an *abstract syntax graph* instead of a tree.

Embedded DSLs come in many flavors. Popular approaches, specifically for Scala, are fluent interfaces, Quasi-Quotation, Abstract Data Types (ADTs), Generalized Abstract Data Types (GADTs), Tagless Final, Free Monads, and Lightweight Modular Staging. How these are implemented in Scala is covered in section 4.