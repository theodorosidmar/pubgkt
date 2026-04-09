package pubgkt;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pubgkt.PlayersApiKt.*;

public class PlayersSample {
    static void main(String[] args) throws ExecutionException, InterruptedException {
        PubgApi api = new PubgApi("");
        api.setPlatform(Platform.STEAM);
        CompletableFuture<String> result = new CompletableFuture<>();
        getPlayerByAccountId(api, "account.c766e217ed7345499ac1b342de1de0dd", new CustomContinuation<>(result));
        System.out.println(result.get());
    }
}

class CustomContinuation<T> implements Continuation<T> {
    private final CompletableFuture<T> future;

    public CustomContinuation(CompletableFuture<T> future) {
        this.future = future;
    }

    @Override
    public void resumeWith(@NotNull Object o) {
        if (o instanceof Result.Failure)
            future.completeExceptionally(((Result.Failure) o).exception);
        else
            future.complete((T) o);
    }

    @NotNull
    @Override
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
