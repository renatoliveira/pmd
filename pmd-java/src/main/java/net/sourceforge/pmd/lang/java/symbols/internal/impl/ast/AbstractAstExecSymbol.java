/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.symbols.internal.impl.ast;

import java.util.List;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.sourceforge.pmd.lang.java.ast.ASTMethodOrConstructorDeclaration;
import net.sourceforge.pmd.lang.java.symbols.JClassSymbol;
import net.sourceforge.pmd.lang.java.symbols.JExecutableSymbol;
import net.sourceforge.pmd.lang.java.symbols.JFormalParamSymbol;
import net.sourceforge.pmd.util.CollectionUtil;

/**
 * @author Clément Fournier
 */
abstract class AbstractAstExecSymbol<T extends ASTMethodOrConstructorDeclaration>
    extends AbstractAstTParamOwner<T>
    implements JExecutableSymbol {

    private final JClassSymbol owner;
    private List<JFormalParamSymbol> formals;

    protected AbstractAstExecSymbol(T node, AstSymFactory factory, JClassSymbol owner) {
        super(node, factory);
        this.owner = owner;
        formals = CollectionUtil.map(
            node.getFormalParameters(),
            p -> new AstFormalParamSym(p.getVarId(), factory, this)
        );
    }

    @Override
    public List<JFormalParamSymbol> getFormalParameters() {
        return formals;
    }

    @Override
    public @NonNull JClassSymbol getEnclosingClass() {
        return owner;
    }


    @Override
    public boolean isVarargs() {
        return node.isVarargs();
    }

    @Override
    public int getArity() {
        return node.getArity();
    }


}
