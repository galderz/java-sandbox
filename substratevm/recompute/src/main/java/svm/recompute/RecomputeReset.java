package svm.recompute;

import java.nio.ByteBuffer;

public class RecomputeReset
{
    private static final ByteBuffer BUFFER = ByteBuffer.allocateDirect(0);

    public ByteBuffer buffer()
    {
        return BUFFER;
    }
}
