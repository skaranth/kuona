package kuona;

import kuona.subversion.Revision;
import kuona.subversion.SubversionLogParser;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProcessTests {
    @Test
    public void capturesOutput() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("svn", "log", "--diff", "-v", getTestRepositoryPath());

        final Process process = builder.start();

        final InputStream inputStream = process.getInputStream();

        ArrayList<Revision> revisions = new ArrayList<>();
        SubversionLogParser parser = new SubversionLogParser(revisions::add);

        parser.parse(inputStream);

        inputStream.close();

        assertThat(revisions.size(), is(greaterThan(1)));
        final Revision firstRevision = revisions.get(revisions.size() - 1);
        assertThat(firstRevision.getRevisionNumber(), is(1));
        assertThat(firstRevision.getUsername(), is("graham"));
        assertThat(firstRevision.getChangedPaths().size(), is(1));
    }

    private String getTestRepositoryPath() {
        return "file://" + Paths.get("").toAbsolutePath().toString() + "/svn-test-repo";
    }
}
