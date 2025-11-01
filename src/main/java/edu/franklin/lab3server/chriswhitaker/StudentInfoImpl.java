/**
 * Purpose: gRPC StudentInfo service (Lab 3). Adds and retrieves Student records using an in-memory repository.
 * Author: Chris Whitaker
 */
package edu.franklin.lab3server.chriswhitaker;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

@GrpcService
public class StudentInfoImpl extends StudentInfoGrpc.StudentInfoImplBase {
    private static final Logger LOG = Logger.getLogger(StudentInfoImpl.class);

    // In-memory repository is a HashMap<id, Student>.
    private final Map<String, Student> repository = new HashMap<>();
    private int sequence = 0;

    @Override
    public void addStudent(Student request, StreamObserver<StudentID> responseObserver) {

        // Generate a new id, store the student
        String id = Integer.toString(++sequence);
        LOG.debugf("addStudent id=%s name=%s phone=%s age=%s zip=%s", id, request.getName(),
                request.getPhone(),
                request.getAge(), request.getZip());
        repository.put(id, request);

        // Respond with the new StudentID then complete the stream
        responseObserver.onNext(StudentID.newBuilder().setValue(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudent(StudentID request, StreamObserver<Student> responseObserver) {
        String id = request.getValue();
        LOG.debugf("getStudent id=%s", id);

        Student student = repository.get(id);

        // If not found, return NOT_FOUND with the description.
        if (student == null) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("The student with the id of " + id + " cannot be found on the server.").asRuntimeException());
            return;
        }

        // If found, send the Student and complete
        responseObserver.onNext(student);
        responseObserver.onCompleted();
    }
}
